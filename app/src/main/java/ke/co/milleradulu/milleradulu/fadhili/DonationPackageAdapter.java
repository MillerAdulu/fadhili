package ke.co.milleradulu.milleradulu.fadhili;

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

public class DonationPackageAdapter extends RecyclerView.Adapter<DonationPackageAdapter.MyViewHolder>{
    private Context fadhiliContext;
    private List<DonationPackage> donationPackageList;

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

    public DonationPackageAdapter(Context fadhiliContext, List<DonationPackage> donationPackageList){
        this.fadhiliContext = fadhiliContext;
        this.donationPackageList = donationPackageList;
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
        DonationPackage donationPackage = donationPackageList.get(position);
        holder.donationPackageTitle.setText(donationPackage.getPackageName());
        holder.donationPackagePrice.setText(donationPackage.getPackagePrice());

        Glide.with(fadhiliContext).load(donationPackage.getPackageThumbnail()).into(holder.donationPackageImage);
    }

    @Override
    public int getItemCount() {
        return donationPackageList.size();
    }
}
