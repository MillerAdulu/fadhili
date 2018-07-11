package ke.co.milleradulu.milleradulu.fadhili.apihandler.clients;

import java.util.List;

import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Purchase;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

  @FormUrlEncoded
  @POST ("/purchase")
  Call<Purchase> addToCart (
    @Field("donorId") Integer donorId,
    @Field("donationId") Integer donationId,
    @Field("donationAmount") Double donationAmount
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
