package ke.co.milleradulu.milleradulu.fadhili.models;

public class Post {
  private int id;
  private String title, excerpt, body, image, slug, createdAt, updatedAt;

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getExcerpt() {
    return excerpt;
  }

  public String getBody() {
    return body;
  }

  public String getImage() {
    return image;
  }

  public String getSlug() {
    return slug;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }
}
