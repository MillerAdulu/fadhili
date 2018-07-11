package ke.co.milleradulu.milleradulu.fadhili.posts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import ke.co.milleradulu.milleradulu.fadhili.R;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Comment;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

  private Context commentContext;
  private List<Comment> commentList;

  public CommentAdapter(Context commentContext, List<Comment> commentList) {
    this.commentContext = commentContext;
    this.commentList = commentList;
  }

  class MyViewHolder extends RecyclerView.ViewHolder {

    TextView commentId, commentDonorName, commentBody;

    public MyViewHolder(View view) {
      super(view);
      commentId = view.findViewById(R.id.comment_id);
      commentDonorName = view.findViewById(R.id.comment_donor_name);
      commentBody = view.findViewById(R.id.comment_body);
    }
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View commentRecyclerView = LayoutInflater.from(
      commentContext
    ).inflate(
      R.layout.card_comment, parent, false
    );

    return new MyViewHolder(commentRecyclerView);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    Comment comment = commentList.get(position);

    holder.commentId.setText(
      String.format(
        Locale.ENGLISH,
        "%d",
        comment.getCommentId()
      )
    );
    holder.commentDonorName.setText(comment.getCommentDonorUserName());
    holder.commentBody.setText(comment.getCommentBody());

  }

  @Override
  public int getItemCount() {
    return commentList.size();
  }
}
