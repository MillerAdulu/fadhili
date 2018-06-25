package ke.co.milleradulu.milleradulu.fadhili.models;

public class Purchase {
  private Integer id, donorId, donationId, paymentStatus, createdAt, updatedAt;

  public Purchase(Integer donorId, Integer donationId, Integer paymentStatus) {
    this.donorId = donorId;
    this.donationId = donationId;
    this.paymentStatus = paymentStatus;
  }

  public Integer getId() {
    return id;
  }
}
