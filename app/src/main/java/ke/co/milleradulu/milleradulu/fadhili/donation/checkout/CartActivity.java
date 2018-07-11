package ke.co.milleradulu.milleradulu.fadhili.donation.checkout;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ke.co.milleradulu.milleradulu.fadhili.R;
import ke.co.milleradulu.milleradulu.fadhili.SessionManagement;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.APIHelper;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.APIServiceProvider;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.clients.DonationClient;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Donation;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Purchase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements  CartClickListener {

  public static final String TAG = CartActivity.class.getSimpleName();

  TextView totalAmount;
  RecyclerView donationItemsRecyclerView;
  CartAdapter donationItemsAdapter;
  List<Donation> donationItemsList;
  ArrayList<Purchase> purchaseList = new ArrayList<>();
  ArrayList<Donation> finalDonationItemsList = new ArrayList<>();
  SessionManagement session;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart);

    session = new SessionManagement(this);
    session.checkLogin();
    purchaseList = session.getCartItems();

    totalAmount = findViewById(R.id.total_amount);
    totalAmount.setText(
      session.getKeyCartTotal()
    );
    donationItemsRecyclerView = findViewById(R.id.donation_cart_recycler);

    RecyclerView.LayoutManager donationItemsLayoutManager = new LinearLayoutManager(
      getApplicationContext(),
      LinearLayoutManager.VERTICAL,
      false
    );

    donationItemsRecyclerView.setLayoutManager(donationItemsLayoutManager);

    prepareRecyclerView();
  }

  private void prepareRecyclerView() {
    DonationClient donationClient = APIServiceProvider.createService(DonationClient.class);
    Call<List<Donation>> donationCall = donationClient.donations();

    APIHelper.enqueWithRetry(donationCall, new Callback<List<Donation>>() {
      @Override
      public void onResponse(@NonNull Call<List<Donation>> call, @NonNull Response<List<Donation>> response) {
        donationItemsList = response.body();

        Log.d("LIST", purchaseList.toString());

        for(Donation donation : donationItemsList) {
          for(Purchase purchase : purchaseList) {
            if(donation.getDonationId() == purchase.getDonationId()) {
              finalDonationItemsList.add(donation);
            }
          }
        }

        Log.d("LIST", finalDonationItemsList.toString());

        donationItemsAdapter = new CartAdapter(
          CartActivity.this,
          finalDonationItemsList
        );
        donationItemsAdapter.setClickListener(CartActivity.this);
        donationItemsRecyclerView.setAdapter(donationItemsAdapter);

      }

      @Override
      public void onFailure(@NonNull Call<List<Donation>> call, @NonNull Throwable t) {
        Log.d(TAG, t.getMessage());
      }
    });
  }
  public void checkOut(View view) {
  }

  @Override
  public void removeFromCart(View view, int position) {
    Donation donation = finalDonationItemsList.get(position);

    finalDonationItemsList.remove(donation);
    ArrayList<Purchase> purchases = new ArrayList<>();

    for(Donation donationItem : finalDonationItemsList) {
      for(Purchase purchase : purchaseList) {
        if(donationItem.getDonationId() == purchase.getDonationId()) {
          purchases.add(purchase);
        }
      }
    }

    session.setCartItems(purchases);
    donationItemsList.clear();
    finalDonationItemsList.clear();
    donationItemsAdapter.notifyDataSetChanged();

    prepareRecyclerView();
  }
}
