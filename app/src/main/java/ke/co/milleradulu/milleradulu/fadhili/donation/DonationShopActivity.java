package ke.co.milleradulu.milleradulu.fadhili.donation;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ke.co.milleradulu.milleradulu.fadhili.R;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.APIHelper;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.APIServiceProvider;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.clients.DonationClient;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Donation;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Purchase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationShopActivity extends AppCompatActivity implements PackageClickListener {
  public static final String TAG = DonationShopActivity.class.getSimpleName();

  RecyclerView donationItemsRecyclerView;
  DonationAdapter donationItemsAdapter;
  List<Donation> donationItemsList;
  ArrayList<Purchase> purchaseList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.donation_package_recycler);

    donationItemsRecyclerView = findViewById(R.id.donation_packages_list_recycler_view);

    RecyclerView.LayoutManager donationItemsLayoutManager = new LinearLayoutManager(
      getApplicationContext(),
      LinearLayoutManager.VERTICAL,
      false
    );

    donationItemsRecyclerView.setLayoutManager(donationItemsLayoutManager);

    DonationClient donationClient = APIServiceProvider.createService(DonationClient.class);
    Call<List<Donation>> donationItemsCall = donationClient.donations();

    APIHelper.enqueWithRetry(donationItemsCall, new Callback<List<Donation>>() {
      @Override
      public void onResponse(@NonNull Call<List<Donation>> call, @NonNull Response<List<Donation>> response) {
        donationItemsList = response.body();
        Log.d(TAG, response.toString());

        donationItemsAdapter = new DonationAdapter(
          DonationShopActivity.this,
          donationItemsList
        );
        donationItemsAdapter.setClickListener(DonationShopActivity.this);
        donationItemsRecyclerView.setAdapter(donationItemsAdapter);

      }

      @Override
      public void onFailure(@NonNull Call<List<Donation>> call, @NonNull Throwable t) {
        Log.e(TAG, t.getMessage());
      }
    });

  }

  @Override
  public void onClick(View view, int position) {
    final Donation donation = donationItemsList.get(position);


    Purchase newPurchase = new Purchase();

    newPurchase.setDonorId(1);
    newPurchase.setDonationId(donation.getDonationId());

    purchaseList.add(newPurchase);
    Toast.makeText(
      this,
      donation.getDonationName() + " added to cart",
      Toast.LENGTH_SHORT
    ).show();

    Log.d(TAG,
      String.format(
        Locale.ENGLISH,
        "%d",
        donation.getDonationId()
      )
    );
  }
}
