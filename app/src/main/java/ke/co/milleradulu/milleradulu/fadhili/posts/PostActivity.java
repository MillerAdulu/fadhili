package ke.co.milleradulu.milleradulu.fadhili.posts;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ke.co.milleradulu.milleradulu.fadhili.R;
import ke.co.milleradulu.milleradulu.fadhili.SessionManagement;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.APIHelper;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.APIServiceProvider;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.clients.CommentClient;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.clients.PostClient;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Comment;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

  private static final String TAG = PostActivity.class.getSimpleName();

  SessionManagement session;
  TextView postViewTitle, postViewBody, postViewCreatedAt;
  EditText addComment;
  ImageView postViewImage;
  Post post = new Post();
  List<Comment> commentList;
  CommentAdapter commentAdapter;
  RecyclerView commentsRecyclerView;
  Comment resultComment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_post);

    session = new SessionManagement(this);
    session.checkLogin();

    postViewImage = findViewById(R.id.post_view_image);
    postViewTitle = findViewById(R.id.post_view_title);
    postViewBody = findViewById(R.id.post_view_body);
    postViewCreatedAt = findViewById(R.id.post_view_created_at);
    addComment = findViewById(R.id.post_comment_adder);
    commentsRecyclerView = findViewById(R.id.comments_recycler_view);

    fetchPost();
  }

  private void fetchComments() {

    RecyclerView.LayoutManager commentsLayoutManager = new LinearLayoutManager(
      getApplicationContext(),
      LinearLayoutManager.VERTICAL,
      false
    );

    commentsRecyclerView.setLayoutManager(commentsLayoutManager);

    CommentClient commentClient = APIServiceProvider.createService(CommentClient.class);
    Call<List<Comment>> commentCall = commentClient.getPostComments(
      session.getPostId()
    );

    APIHelper.enqueWithRetry(commentCall, new Callback<List<Comment>>() {
      @Override
      public void onResponse(@NonNull Call<List<Comment>> call, @NonNull Response<List<Comment>> response) {
        commentList = response.body();
        if(commentList.size() == 0) return;

        Log.d(TAG, response.toString());

        commentAdapter = new CommentAdapter(
          PostActivity.this,
          commentList
        );

        commentsRecyclerView.setAdapter(commentAdapter);

      }

      @Override
      public void onFailure(@NonNull Call<List<Comment>> call, @NonNull Throwable t) {
        Log.e(TAG, t.getMessage());
      }
    });
  }

  private void fetchPost() {
    PostClient postClient = APIServiceProvider.createService(PostClient.class);
    Call<Post> postCall = postClient.post(
      session.getPostId()
    );

    Log.d("AAAAAAAAAAAAAA", session.getPostId());

    APIHelper.enqueWithRetry(postCall, new Callback<Post>() {
      @Override
      public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
        post = response.body();

        displayPost();
      }

      @Override
      public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
        Log.d(TAG, t.getMessage());
      }
    });
  }

  private void displayPost() {
    postViewTitle.setText(post.getPostTitle());
    postViewBody.setText(
      Html.fromHtml(
        post.getPostBody()
      )
    );
    postViewCreatedAt.setText(post.getCreatedAt());

    Glide.with(this)
      .load(
        "https://cdn.pixabay.com/photo/2016/08/06/14/11/money-1574450_960_720.png"
      ).into(postViewImage);

    fetchComments();
  }

  public void addComment(View view) {
    String commentText = addComment.getText().toString();
    Comment comment = new Comment();

    comment.setDonorId(
      Integer.parseInt(
        session.getDonorId()
      )
    );

    comment.setCommentPostId(
      Integer.parseInt(
        session.getPostId()
      )
    );

    comment.setCommentBody(commentText);

    CommentClient commentClient = APIServiceProvider.createService(CommentClient.class);
    Call<Comment> commentCall = commentClient.addComment(
      comment.getDonorId(),
      comment.getCommentPostId(),
      comment.getCommentBody()
    );

    APIHelper.enqueWithRetry(commentCall, new Callback<Comment>() {
      @Override
      public void onResponse(@NonNull Call<Comment> call, @NonNull Response<Comment> response) {
        resultComment = response.body();
        fetchComments();
      }

      @Override
      public void onFailure(@NonNull Call<Comment> call, @NonNull Throwable t) {
        Log.e(TAG, t.getMessage());
      }
    });
  }
}
