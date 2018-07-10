package ke.co.milleradulu.milleradulu.fadhili.apihandler.clients;

import java.util.List;

import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.CollectionCenter;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CollectionCenterClient {
  @GET ("/collectioncenter")
  Call<List<CollectionCenter>> collectionCenters();
}
