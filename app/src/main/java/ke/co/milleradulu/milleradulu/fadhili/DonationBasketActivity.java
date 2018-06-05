package ke.co.milleradulu.milleradulu.fadhili;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DonationBasketActivity extends AppCompatActivity {

    private RecyclerView donationPackageRecyclerView;
    private DonationPackageAdapter donationPackageAdapter;
    private List<DonationPackage> donationPackageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_basket);

        Toolbar toolbar = findViewById(R.id.donation_packages_list_toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();

        donationPackageRecyclerView = findViewById(R.id.donation_packages_list_recycler_view);

        donationPackageList = new ArrayList<>();
        donationPackageAdapter = new DonationPackageAdapter(this, donationPackageList);

        RecyclerView.LayoutManager donationPackageLayoutManager = new GridLayoutManager(this, 2);
        donationPackageRecyclerView.setLayoutManager(donationPackageLayoutManager);
        donationPackageRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        donationPackageRecyclerView.setItemAnimator(new DefaultItemAnimator());
        donationPackageRecyclerView.setAdapter(donationPackageAdapter);

        prepareDonationPackages();

        try {
            Glide.with(this).load(R.drawable.ic_add_to_basket).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void prepareDonationPackages() {
        int[] donationPackages = new int[];

        donationPackageAdapter.notifyDataSetChanged();
    }
    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.donation_packages_list_collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        AppBarLayout donationPackagesAppBarLayout = findViewById(R.id.donation_packages_list_appbar);
        donationPackagesAppBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        donationPackagesAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration{
        private int spanCount, spacing;
        private boolean includeEdge;

        GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge){
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}

