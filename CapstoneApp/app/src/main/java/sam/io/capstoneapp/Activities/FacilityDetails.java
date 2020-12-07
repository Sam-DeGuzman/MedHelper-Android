package sam.io.capstoneapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import sam.io.capstoneapp.R;

public class FacilityDetails extends AppCompatActivity {
    TextView name,contact_number1,address1,affiliation,services1,offers1,hmo1,schedule1;
    ImageView cont_img;
    FloatingActionButton detailsfab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_details);
        AppBarLayout appBarLayout = findViewById(R.id.app_bar_call);
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar2);
        final Toolbar toolbar = findViewById(R.id.toptool2);

        detailsfab = findViewById(R.id.fabins2);
        name = findViewById(R.id.contact_name);
        cont_img = findViewById(R.id.contact_image);
        contact_number1 = findViewById(R.id.contact_number);
        address1 = findViewById(R.id.contact_address);
        affiliation = findViewById(R.id.affiliation);
        services1 = findViewById(R.id.Services);
        offers1 = findViewById(R.id.Offers);
        hmo1 = findViewById(R.id.HMO);
        schedule1 = findViewById(R.id.service_sched);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton floatingActionButton = findViewById(R.id.fabins2);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacilityDetails.this,App_CallDirectory.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        final String cont_name = bundle.getString("Name");
        String img = bundle.getString("img");
        final String cont_number = bundle.getString("num");
        String cont_address = bundle.getString("address");
        String cont_affiliation = bundle.getString("affiliation");
        String cont_services = bundle.getString("services");
        String cont_hmo = bundle.getString("HMO");
        String offers = bundle.getString("Offers");
        String sched = bundle.getString("sched");


        name.setText(cont_name);
        Picasso.get().load(img).into(cont_img);
        contact_number1.setText(cont_number);
        address1.setText(cont_address);
        schedule1.setText(sched);
        affiliation.setText(cont_affiliation);
        services1.setText(cont_services.replaceAll(",","\n"));
        hmo1.setText(cont_hmo.replaceAll(",","\n"));
        offers1.setText(offers.replaceAll(",","\n"));

        // name.setText(facility_name);
        // collapsingToolbarLayout.setTitle(facility_name);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(cont_name);
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

        detailsfab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+ cont_number));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}
