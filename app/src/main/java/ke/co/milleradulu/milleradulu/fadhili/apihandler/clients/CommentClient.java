package ke.co.milleradulu.milleradulu.fadhili.apihandler.clients;

import java.util.List;

import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Comment;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentClient {

  @GET ("/comment/{post_id}")
  Call<List<Comment>> getPostComments(
    @Path("post_id") String postId
  );

  @FormUrlEncoded
  @POST ("/comment/add")
  Call<Comment> addComment(
    @Field("donorId") Integer donorId,
    @Field("postId") Integer postId,
    @Field("commentBody") String commentBody
  );

}
