package ke.co.milleradulu.milleradulu.fadhili.apihandler.clients;

import java.util.List;

import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Donor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DonorClient {

  @GET ("/donor/")
  Call<List<Donor>> donors();

  @GET ("/donor/{id}")
  Call<Donor> donor(
    @Path("id") int id
  );

  @POST ("/donor/")
  Call<Donor> registerDonor(
    @Body Donor donor
  );

  @POST ("donor/login")
  Call<Donor> login(
    @Body Donor donor
  );

  @POST ("donor/update")
  Call<Donor> update (@Body Donor donor);

  @GET ("/donor/{id}")
  Call<Donor> delete (
    @Path("id") int id
  );
}
