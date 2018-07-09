package ke.co.milleradulu.milleradulu.fadhili.apihandler.clients;

import java.util.List;

import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Destination;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DestinationClient {

  @GET ("/destination")
  Call<List<Destination>> destinations();

  @GET ("/destination/{id}")
  Call<Destination> destination (
    @Path("id") int id
  );

}
