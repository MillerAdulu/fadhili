package ke.co.milleradulu.milleradulu.fadhili.apihandler.models;

public class Destination {

  private Integer destinationId;
  private String destinationName, destinationCrisis, destinationLocation, createdAt, updatedAt;

  public Integer getDestinationId() {
    return destinationId;
  }

  public void setDestinationId(Integer destinationId) {
    this.destinationId = destinationId;
  }

  public String getDestinationName() {
    return destinationName;
  }

  public void setDestinationName(String destinationName) {
    this.destinationName = destinationName;
  }

  public String getDestinationCrisis() {
    return destinationCrisis;
  }

  public void setDestinationCrisis(String destinationCrisis) {
    this.destinationCrisis = destinationCrisis;
  }

  public String getDestinationLocation() {
    return destinationLocation;
  }

  public void setDestinationLocation(String destinationLocation) {
    this.destinationLocation = destinationLocation;
  }
}
