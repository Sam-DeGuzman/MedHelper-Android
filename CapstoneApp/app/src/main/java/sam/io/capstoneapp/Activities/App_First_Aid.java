package sam.io.capstoneapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import sam.io.capstoneapp.Adapters.FirstAidAdapter;
import sam.io.capstoneapp.Models.FirstAidItems;
import sam.io.capstoneapp.R;

public class App_First_Aid extends AppCompatActivity{
    private RecyclerView aidRecyclerView2;
    private RecyclerView.Adapter aidAdapter;
    private RecyclerView.LayoutManager aidLayoutManager;
    private BottomAppBar bottomfirstaid;
    private FloatingActionButton aidFab;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app__first__aid);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Emergency First Aid");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App_First_Aid.super.onBackPressed();
            }
        });

        bottomfirstaid = findViewById(R.id.Bottom_App_Bar_FirstAid);
        bottomfirstaid.replaceMenu(R.menu.app_bar_menu);
        bottomfirstaid.setOverflowIcon(ContextCompat.getDrawable(this,R.drawable.ic_more_vert_24));



        bottomfirstaid.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(App_First_Aid.this,App_Home.class);
                startActivity(goHome);
            }
        });

        bottomfirstaid.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.call:
                        Intent goCall = new Intent(App_First_Aid.this,App_CallDirectory.class);
                        startActivity(goCall);
                        break;
                    case R.id.firstaid:
                        Toast.makeText(App_First_Aid.this,"Already in First Aid Screen",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.bottomabout:
                        Intent goAbout = new Intent(App_First_Aid.this, App_About.class);
                        startActivity(goAbout);
                        break;
                    case R.id.bottomhelp:
                        Intent goHelp = new Intent(App_First_Aid.this,App_Help.class);
                        startActivity(goHelp);
                        break;
                }
                return false;
            }
        });


        ArrayList<FirstAidItems> FAitems = new ArrayList<>();
        FAitems.add(new FirstAidItems(R.drawable.ischeamicheartdisease,"Heart Attack"));
        FAitems.add(new FirstAidItems(R.drawable.stroke ,"Stroke"));
        FAitems.add(new FirstAidItems(R.drawable.asthma,"Asthma"));
        FAitems.add(new FirstAidItems(R.drawable.pneumonia,"Pneumonia"));
        FAitems.add(new FirstAidItems(R.drawable.diabetes,"Diabetes"));
        FAitems.add(new FirstAidItems(R.drawable.hypertensive,"High Blood Pressure"));
        FAitems.add(new FirstAidItems(R.drawable.burns,"Burns"));
        FAitems.add(new FirstAidItems(R.drawable.eyeinjury,"Eye Injury"));
        FAitems.add(new FirstAidItems(R.drawable.electricshock,"Electric Shock"));
        FAitems.add(new FirstAidItems(R.drawable.shock,"Shock"));
        FAitems.add(new FirstAidItems(R.drawable.dogbite,"Dog Bite"));



        aidRecyclerView2 = findViewById(R.id.FArecycler_view);
        aidRecyclerView2.setHasFixedSize(true);
        aidLayoutManager = new LinearLayoutManager(this);
        aidAdapter = new FirstAidAdapter(FAitems);

        aidRecyclerView2.setLayoutManager(aidLayoutManager);
        aidRecyclerView2.setAdapter(aidAdapter);

        aidFab = findViewById(R.id.aidFAB);
        aidFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotolocation = new Intent(App_First_Aid.this,MapActivity.class);
                startActivity(gotolocation);
            }
        });

        LocationManager manager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            aidFab.setImageResource(R.drawable.gpsoffstyle);
        }
        else {
            aidFab.setImageResource(R.drawable.gps_iconstyle);
        }



        aidRecyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0 && ! aidFab.isShown())
                    aidFab.show();
                else if(dy > 0 && aidFab.isShown())
                    aidFab.hide();
            }
        });

    }
}
