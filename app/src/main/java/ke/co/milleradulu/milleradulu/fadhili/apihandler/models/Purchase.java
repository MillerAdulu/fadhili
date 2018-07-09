package ke.co.milleradulu.milleradulu.fadhili.apihandler.models;

public class Purchase {
  private Integer purchaseId, donorId, donationId, paymentStatus, createdAt, updatedAt;

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
}
