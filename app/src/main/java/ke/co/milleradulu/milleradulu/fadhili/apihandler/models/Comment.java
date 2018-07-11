package ke.co.milleradulu.milleradulu.fadhili.apihandler.models;

public class Comment {
  private Integer commentId, commentPostId, donorId;
  private String commentDonorUserName, commentBody, createdAt, updatedAt;

  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  public Integer getCommentPostId() {
    return commentPostId;
  }

  public Integer getDonorId() {
    return donorId;
  }

  public void setDonorId(Integer donorId) {
    this.donorId = donorId;
  }

  public void setCommentPostId(Integer commentPostId) {
    this.commentPostId = commentPostId;
  }

  public String getCommentDonorUserName() {
    return commentDonorUserName;
  }

  public void setCommentDonorUserName(String commentDonorUserName) {
    this.commentDonorUserName = commentDonorUserName;
  }

  public String getCommentBody() {
    return commentBody;
  }

  public void setCommentBody(String commentBody) {
    this.commentBody = commentBody;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}
