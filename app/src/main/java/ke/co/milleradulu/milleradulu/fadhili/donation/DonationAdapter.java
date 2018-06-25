package ke.co.milleradulu.milleradulu.fadhili.donation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import ke.co.milleradulu.milleradulu.fadhili.R;
import ke.co.milleradulu.milleradulu.fadhili.models.Donation;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.MyViewHolder>{
  private Context fadhiliContext;
  private List<Donation> donationList;
  private ArrayMap<String, Integer> donationMap = new ArrayMap<>();

  class MyViewHolder extends RecyclerView.ViewHolder{
    TextView donationPackageTitle, donationPackagePrice;
    ImageView donationPackageImage;

    MyViewHolder(View view){
      super(view);
      donationPackageTitle = view.findViewById(R.id.donation_package_title);
      donationPackagePrice = view.findViewById(R.id.donation_package_price);
      donationPackageImage = view.findViewById(R.id.donation_package_image);
    }
  }

  public DonationAdapter(Context fadhiliContext, List<Donation> donationList){
    this.fadhiliContext = fadhiliContext;
    this.donationList = donationList;

    for (Donation donationItem: donationList) {
      donationMap.put(donationItem.getImage(), donationItem.getId());
    }
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View donationPackageView = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.donation_package_card, parent, false);
    return new MyViewHolder(donationPackageView);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Donation donation = donationList.get(position);
    holder.donationPackageTitle.setText(donation.getName());
    holder.donationPackagePrice.setText(
      String.format(
        Locale.ENGLISH,
        "%.2f",
        donation.getPrice()
      )
    );

    Glide.with(fadhiliContext).load(donation.getImage()).into(holder.donationPackageImage);
  }

  @Override
  public int getItemCount() {
    return donationList.size();
  }
}
