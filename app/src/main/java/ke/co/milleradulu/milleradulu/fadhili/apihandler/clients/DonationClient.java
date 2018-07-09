package ke.co.milleradulu.milleradulu.fadhili.apihandler.clients;

import java.util.List;

import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Donation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DonationClient {

  @GET ("/donation")
  Call<List<Donation>> donations ();

  @GET ("/donation/{id}")
  Call<Donation> donation (
    @Path("id") int id
  );
}
