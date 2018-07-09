package ke.co.milleradulu.milleradulu.fadhili.donation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import ke.co.milleradulu.milleradulu.fadhili.R;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Donation;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.MyViewHolder>{
  private Context fadhiliContext;
  private List<Donation> donationList;
  private PackageClickListener listener;

  public void setClickListener(PackageClickListener listener) {
    this.listener = listener;
  }

  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView donationPackageTitle, donationPackagePrice, donationPackageId;
    ImageView donationPackageImage;


    MyViewHolder(View view){
      super(view);
      donationPackageTitle = view.findViewById(R.id.donation_package_title);
      donationPackagePrice = view.findViewById(R.id.donation_package_price);
      donationPackageImage = view.findViewById(R.id.donation_package_image);
      donationPackageId = view.findViewById(R.id.donation_package_id);

      view.setOnClickListener(this);
      donationPackagePrice.setOnClickListener(this);
      donationPackageTitle.setOnClickListener(this);
      donationPackageImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      if(listener != null) listener.onClick(v, getAdapterPosition());
    }
  }

  DonationAdapter(Context fadhiliContext, List<Donation> donationList){
    this.donationList = donationList;
    this.fadhiliContext = fadhiliContext;
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
    holder.donationPackageTitle.setText(donation.getDonationName());
    holder.donationPackagePrice.setText(
      String.format(
        Locale.ENGLISH,
        "%.2f",
        donation.getDonationPrice()
      )
    );
    holder.donationPackageId.setText(
      String.format(
        Locale.ENGLISH,
        "%d",
        donation.getDonationId()
      )
    );
    Glide.with(fadhiliContext).load(
      "https://cdn.pixabay.com/photo/2016/08/06/14/11/money-1574450_960_720.png"
    ).into(holder.donationPackageImage);
  }

  @Override
  public int getItemCount() {
    return donationList.size();
  }
}
