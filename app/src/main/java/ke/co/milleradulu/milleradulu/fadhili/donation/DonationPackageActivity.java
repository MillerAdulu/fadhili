package ke.co.milleradulu.milleradulu.fadhili.donation;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Locale;

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

public class DonationPackageActivity extends AppCompatActivity {

  private static final String TAG = DonationPackageActivity.class.getSimpleName();

  TextView singleDonationPackageTitle, singleDonationPackagePrice, singleDonationPackageId,
    singleDonationPackageDestination, singleDonationPackageContents;
  ImageView singleDonationPackageImage;
  SessionManagement session;
  Donation donation;
  ArrayList<Purchase> purchaseArrayList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_donation_package);

    session = new SessionManagement(this);
    session.checkLogin();
    purchaseArrayList = session.getCartItems();

    singleDonationPackageTitle = findViewById(R.id.single_donation_package_title);
    singleDonationPackagePrice = findViewById(R.id.single_donation_package_price);
    singleDonationPackageId = findViewById(R.id.single_donation_package_id);
    singleDonationPackageDestination = findViewById(R.id.single_donation_package_destination);
    singleDonationPackageImage = findViewById(R.id.single_donation_package_image);
    singleDonationPackageContents = findViewById(R.id.single_donation_package_contents);

    getPackage();
  }

  private void getPackage() {
    DonationClient donationClient = APIServiceProvider.createService(DonationClient.class);
    Call<Donation> donationCall = donationClient.donation(
      Integer.parseInt(
        session.getKeyDonationItemId()
      )
    );

    APIHelper.enqueWithRetry(donationCall, new Callback<Donation>() {
      @Override
      public void onResponse(@NonNull Call<Donation> call, @NonNull Response<Donation> response) {
        Log.d(TAG, response.toString());
        donation = response.body();
        displayPackage();
      }

      @Override
      public void onFailure(@NonNull Call<Donation> call, @NonNull Throwable t) {
        Log.d(TAG, t.getMessage());
      }
    });
  }

  private void displayPackage() {
    singleDonationPackageId.setText(
      String.format(
        Locale.ENGLISH,
        "%d",
        donation.getDonationId()
      )
    );
    singleDonationPackagePrice.setText(String.format(
      Locale.ENGLISH,
      "%.2f",
      donation.getDonationPrice()
    ));
    singleDonationPackageTitle.setText(donation.getDonationName());
    singleDonationPackageDestination.setText(donation.getDonationDestination());
    singleDonationPackageContents.setText(
      Html.fromHtml(
        donation.getDonationContents()
      )
    );

    Glide.with(this)
      .load(
        "https://cdn.pixabay.com/photo/2016/08/06/14/11/money-1574450_960_720.png"
      ).into(singleDonationPackageImage);
  }

  public void addToCart(View view) {
    final Purchase purchase = new Purchase();

    purchase.setDonorId(
      Integer.parseInt(
        session.getDonorId()
      )
    );

    purchase.setDonationId(
      Integer.parseInt(
        singleDonationPackageId.getText().toString()
      )
    );

    purchase.setPaymentStatus(0);
    purchase.setDonationAmount(
      Double.parseDouble(
        singleDonationPackagePrice.getText().toString()
      )
    );

  }

}
