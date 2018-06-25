package ke.co.milleradulu.milleradulu.fadhili.models;

public class Donor {
  private Integer id;
  private String email;
  private String username;
  private String phoneNumber;
  private String password, createdAt, updatedAt;

  public Donor(String email, String username, String phoneNumber, String password) {
    this.email = email;
    this.username = username;
    this.phoneNumber = phoneNumber;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

}
