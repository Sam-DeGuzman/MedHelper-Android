package sam.io.capstoneapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import sam.io.capstoneapp.R;
import sam.io.capstoneapp.SwipeSample;


public class App_Home extends AppCompatActivity {

    private BottomAppBar bottomHome;
    FloatingActionButton fltbtn;
    MenuItem about;

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home);  // Context : activity_app_home
        // CHECK GPS FUNCTION
        fltbtn = findViewById(R.id.fabhome);
        bottomHome = findViewById(R.id.Bottom_App_Bar_Home);
        bottomHome.replaceMenu(R.menu.app_bar_menu);
        about = findViewById(R.id.bottomabout);

        statusCheck();

        button = findViewById(R.id.swipetest);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(App_Home.this, SwipeSample.class);

                startActivity(go);
            }
        });

        fltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager manager2= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!manager2.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    fltbtn.setImageResource(R.drawable.gpsoffstyle);
                }
                else {
                    fltbtn.setImageResource(R.drawable.gps_iconstyle);
                }
                Intent locsrc = new Intent(App_Home.this, MapActivity.class);
                startActivity(locsrc);
            }

        });

        bottomHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager manager2= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!manager2.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    fltbtn.setImageResource(R.drawable.gpsoffstyle);
                }
                else {
                    fltbtn.setImageResource(R.drawable.gps_iconstyle);
                }
                Toast.makeText(App_Home.this,"Already In Home Screen!", Toast.LENGTH_SHORT).show();
            }
        });
        bottomHome.setOverflowIcon(ContextCompat.getDrawable(this,R.drawable.ic_more_vert_24));

        bottomHome.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.call:
                        LocationManager manager2= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        if (!manager2.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            fltbtn.setImageResource(R.drawable.gpsoffstyle);
                        }
                        else {
                            fltbtn.setImageResource(R.drawable.gps_iconstyle);
                        }
                        Intent goCall = new Intent(App_Home.this,App_CallDirectory.class);
                        startActivity(goCall);
                        break;
                    case R.id.firstaid:
                        LocationManager manager3= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        if (!manager3.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            fltbtn.setImageResource(R.drawable.gpsoffstyle);
                        }
                        else {
                            fltbtn.setImageResource(R.drawable.gps_iconstyle);
                        }
                        Intent goFirstAid = new Intent(App_Home.this,App_First_Aid.class);
                        startActivity(goFirstAid);
                        break;
                    case R.id.bottomabout:
                        LocationManager manager4= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        if (!manager4.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            fltbtn.setImageResource(R.drawable.gpsoffstyle);
                        }
                        else {
                            fltbtn.setImageResource(R.drawable.gps_iconstyle);
                        }
                        Intent goAbout = new Intent(App_Home.this, App_About.class);
                        startActivity(goAbout);
                        break;
                    case R.id.bottomhelp:
                        LocationManager manager5= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        if (!manager5.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            fltbtn.setImageResource(R.drawable.gpsoffstyle);
                        }
                        else {
                            fltbtn.setImageResource(R.drawable.gps_iconstyle);
                        }
                        Intent goHelp = new Intent(App_Home.this,App_Help.class);
                        startActivity(goHelp);
                        break;
                }
                return false;
            }
        });


    }

    private void buildAlertMessageNoGps(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    public void statusCheck(){
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                fltbtn.setImageResource(R.drawable.gpsoffstyle);
            }
            else{
                fltbtn.setImageResource(R.drawable.gps_iconstyle);
            }
        }
        else {
            fltbtn.setImageResource(R.drawable.gps_iconstyle);
        }
    }



}