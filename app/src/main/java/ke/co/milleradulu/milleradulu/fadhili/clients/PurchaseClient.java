package ke.co.milleradulu.milleradulu.fadhili.clients;

import java.util.List;

import ke.co.milleradulu.milleradulu.fadhili.models.Purchase;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PurchaseClient {

  @GET ("/purchase")
  Call<List<Purchase>> purchases();

  @GET ("/purchase/{id}")
  Call<List<Purchase>> myPurchases (
    @Path("id") int id
  );

  @POST ("/purchase")
  Call<Purchase> addToCart (
    @Body Purchase purchase
  );

  @POST ("/purchase/update")
  Call<Purchase> updatePurchase (
    @Body Purchase purchase
  );

  @POST ("/purchase/destroy/{id}")
  Call<Purchase> destroy (
    @Path("id") int id
  );
}
