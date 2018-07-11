package ke.co.milleradulu.milleradulu.fadhili.apihandler.models;

public class Purchase {
  private Integer purchaseId, donorId, donationId, paymentStatus;
  private Double donationAmount;
  private String donationItem, createdAt, updatedAt;

  public Integer getPurchaseId() {
    return purchaseId;
  }

  public void setPurchaseId(Integer purchaseId) {
    this.purchaseId = purchaseId;
  }

  public Integer getDonorId() {
    return donorId;
  }

  public void setDonorId(Integer donorId) {
    this.donorId = donorId;
  }

  public Integer getDonationId() {
    return donationId;
  }

  public void setDonationId(Integer donationId) {
    this.donationId = donationId;
  }

  public Integer getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(Integer paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public Double getDonationAmount() {
    return donationAmount;
  }

  public void setDonationAmount(Double donationAmount) {
    this.donationAmount = donationAmount;
  }
}
