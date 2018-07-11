package ke.co.milleradulu.milleradulu.fadhili.apihandler.models;

public class Donation {

    private int donationId, donationQuantity;
    private String donationName, donationDestination, donationImage, donationContents, createdAt, updatedAt;
    private double donationPrice;

  public int getDonationId() {
    return donationId;
  }

  public void setDonationId(int donationId) {
    this.donationId = donationId;
  }

  public String getDonationName() {
    return donationName;
  }

  public void setDonationName(String donationName) {
    this.donationName = donationName;
  }

  public String getDonationDestination() {
    return donationDestination;
  }

  public void setDonationDestination(String donationDestination) {
    this.donationDestination = donationDestination;
  }

  public String getDonationImage() {
    return donationImage;
  }

  public void setDonationImage(String donationImage) {
    this.donationImage = donationImage;
  }

  public double getDonationPrice() {
    return donationPrice;
  }

  public void setDonationPrice(double donationPrice) {
    this.donationPrice = donationPrice;
  }

  public int getDonationQuantity() {
    return donationQuantity;
  }

  public void setDonationQuantity(int donationQuantity) {
    this.donationQuantity = donationQuantity;
  }

  public String getDonationContents() {
    return donationContents;
  }

  public void setDonationContents(String donationContents) {
    this.donationContents = donationContents;
  }
}
