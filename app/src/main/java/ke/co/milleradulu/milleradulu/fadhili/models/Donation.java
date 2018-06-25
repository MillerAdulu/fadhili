package ke.co.milleradulu.milleradulu.fadhili.models;

public class Donation {

    private int id;
    private String name, destination, image, createdAt, updatedAt;
    private double price;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }
}
