package ke.co.milleradulu.milleradulu.fadhili.clients;

import java.util.List;

import ke.co.milleradulu.milleradulu.fadhili.models.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostClient {

  @GET ("post")
  Call<List<Post>> posts();

  @GET ("post/{id}")
  Call<Post> post(
    @Path("id") int id
  );

}
