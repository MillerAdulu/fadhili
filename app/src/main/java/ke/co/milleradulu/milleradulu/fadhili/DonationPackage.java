package ke.co.milleradulu.milleradulu.fadhili;

public class DonationPackage {
    private int packageThumbnail;
    private String packageName;
    private int packagePrice;

    public DonationPackage(){}
    public DonationPackage(String packageName, int packageThumbnail, int packagePrice){
        this.packageName = packageName;
        this.packageThumbnail = packageThumbnail;
        this.packagePrice = packagePrice;
    }

    public int getPackageThumbnail() {
        return packageThumbnail;
    }

    public void setPackageThumbnail(int packageThumbnail) {
        this.packageThumbnail = packageThumbnail;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(int packagePrice) {
        this.packagePrice = packagePrice;
    }
}
