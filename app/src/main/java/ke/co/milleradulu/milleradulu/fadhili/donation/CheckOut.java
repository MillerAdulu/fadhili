package ke.co.milleradulu.milleradulu.fadhili.donation;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import ke.co.milleradulu.milleradulu.fadhili.apihandler.APIHelper;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.APIServiceProvider;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.clients.PurchaseClient;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Purchase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOut {

  private List<Purchase> purchaseList;

  public void checkOut () {

    for(Purchase purchase: purchaseList) {
      PurchaseClient purchaseClient = APIServiceProvider.createService(PurchaseClient.class);
      Call<Purchase> purchaseCall = purchaseClient.addToCart (
        purchase
      );

      APIHelper.enqueWithRetry(purchaseCall, new Callback<Purchase>() {
        @Override
        public void onResponse(@NonNull Call<Purchase> call, @NonNull Response<Purchase> response) {
          Log.d("CHECKOUT", "Added to online cart");
        }

        @Override
        public void onFailure(@NonNull Call<Purchase> call, @NonNull Throwable t) {
          Log.d("CHECKOUT", "Failed to add to online cart");
        }
      });
    }
  }

}
