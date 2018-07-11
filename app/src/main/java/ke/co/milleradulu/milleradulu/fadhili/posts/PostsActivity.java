package ke.co.milleradulu.milleradulu.fadhili.posts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Locale;

import ke.co.milleradulu.milleradulu.fadhili.R;
import ke.co.milleradulu.milleradulu.fadhili.SessionManagement;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.APIHelper;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.APIServiceProvider;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.clients.PostClient;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsActivity extends AppCompatActivity implements PostClickListener {

  private static final String TAG = PostsActivity.class.getSimpleName();

  RecyclerView postRecyclerView;
  PostAdapter postAdapter;
  List<Post> postList;
  SessionManagement session;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_posts);

    session = new SessionManagement(this);

    session.checkLogin();

    postRecyclerView = findViewById(R.id.fadhili_posts);

    prepareRecyclerView();

  }

  void prepareRecyclerView() {
    RecyclerView.LayoutManager postsLayoutManager = new LinearLayoutManager(
      getApplicationContext(),
      LinearLayoutManager.VERTICAL,
      false
    );

    postRecyclerView.setLayoutManager(postsLayoutManager);

    PostClient postClient = APIServiceProvider.createService(PostClient.class);
    Call<List<Post>> postsCall = postClient.posts();

    APIHelper.enqueWithRetry(postsCall, new Callback<List<Post>>() {
      @Override
      public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
        postList = response.body();
        Log.d(TAG, response.toString());

        postAdapter = new PostAdapter(
          PostsActivity.this,
          postList
        );

        postAdapter.setClickListener(PostsActivity.this);
        postRecyclerView.setAdapter(postAdapter);
      }

      @Override
      public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
        Log.e(TAG, t.getMessage());
      }
    });
  }


  @Override
  public void onClick(View view, int position) {
    final Post post = postList.get(position);
    session.setKeyPostId(
      String.format(
        Locale.ENGLISH,
        "%d",
        post.getPostId()
      )
    );

    loadPost();
  }

  void loadPost() {
    startActivity(
      new Intent(
        PostsActivity.this,
        PostActivity.class
      )
    );
  }
}
