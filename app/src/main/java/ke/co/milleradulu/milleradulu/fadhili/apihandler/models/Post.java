package ke.co.milleradulu.milleradulu.fadhili.apihandler.models;

public class Post {
  private Integer postId;
  private String postTitle, postExcerpt, postBody, postImage, postSlug, createdAt, updatedAt;

  public Integer getPostId() {
    return postId;
  }

  public void setPostId(Integer postId) {
    this.postId = postId;
  }

  public String getPostTitle() {
    return postTitle;
  }

  public void setPostTitle(String postTitle) {
    this.postTitle = postTitle;
  }

  public String getPostExcerpt() {
    return postExcerpt;
  }

  public void setPostExcerpt(String postExcerpt) {
    this.postExcerpt = postExcerpt;
  }

  public String getPostBody() {
    return postBody;
  }

  public void setPostBody(String postBody) {
    this.postBody = postBody;
  }

  public String getPostImage() {
    return postImage;
  }

  public void setPostImage(String postImage) {
    this.postImage = postImage;
  }

  public String getPostSlug() {
    return postSlug;
  }

  public void setPostSlug(String postSlug) {
    this.postSlug = postSlug;
  }
}
