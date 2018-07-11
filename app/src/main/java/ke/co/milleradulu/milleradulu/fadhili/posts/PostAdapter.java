package ke.co.milleradulu.milleradulu.fadhili.posts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ke.co.milleradulu.milleradulu.fadhili.R;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

  private List<Post> postList;
  private Context postContext;
  private PostClickListener listener;

  public PostAdapter(Context postContext, List<Post> postList) {
    this.postList = postList;
    this.postContext = postContext;
  }

  public void setClickListener(PostClickListener listener) {
    this.listener = listener;
  }

  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView postTitle, postExcerpt, postCreatedAt;
    ImageView postImage;

    MyViewHolder(View view) {
      super(view);
      postTitle = view.findViewById(R.id.post_title);
      postExcerpt = view.findViewById(R.id.post_excerpt);
      postCreatedAt = view.findViewById(R.id.post_created_at);
      postImage = view.findViewById(R.id.post_image);

      postTitle.setOnClickListener(this);
      postImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      if(listener != null) listener.onClick(v, getAdapterPosition());
    }
  }

  @NonNull
  @Override
  public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View postRecyclerView = LayoutInflater.from(
      parent.getContext()
    ).inflate(
      R.layout.content_posts, parent, false
    );

    return new MyViewHolder(postRecyclerView);
  }

  @Override
  public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, int position) {
    Post post = postList.get(position);

    holder.postTitle.setText(post.getPostTitle());
    holder.postExcerpt.setText(post.getPostExcerpt());
    holder.postCreatedAt.setText(post.getCreatedAt());

    Glide.with(postContext).load(
      "https://cdn.pixabay.com/photo/2016/08/06/14/11/money-1574450_960_720.png"
    ).into(holder.postImage);
  }

  @Override
  public int getItemCount() {
    return postList.size();
  }
}
