package ke.co.milleradulu.milleradulu.fadhili.apihandler.models;

public class Donor {
  private Integer donorId;
  private String donorEmail;
  private String donorUserName;
  private String donorPhoneNumber;
  private String donorPassword, createdAt, updatedAt;

  public Integer getDonorId() {
    return donorId;
  }

  public void setDonorId(Integer donorId) {
    this.donorId = donorId;
  }

  public String getDonorEmail() {
    return donorEmail;
  }

  public void setDonorEmail(String donorEmail) {
    this.donorEmail = donorEmail;
  }

  public String getDonorUserName() {
    return donorUserName;
  }

  public void setDonorUserName(String donorUserName) {
    this.donorUserName = donorUserName;
  }

  public String getDonorPhoneNumber() {
    return donorPhoneNumber;
  }

  public void setDonorPhoneNumber(String donorPhoneNumber) {
    this.donorPhoneNumber = donorPhoneNumber;
  }

  public String getDonorPassword() {
    return donorPassword;
  }

  public void setDonorPassword(String donorPassword) {
    this.donorPassword = donorPassword;
  }
}
