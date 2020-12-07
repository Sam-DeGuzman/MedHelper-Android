package sam.io.capstoneapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Context;
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

public class App_Help extends AppCompatActivity {

    private BottomAppBar bottomHelp;
    FloatingActionButton fltbtn2;
    MenuItem about2;
    Button appbar, ListVer, ListHor,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app__help);

        fltbtn2 = findViewById(R.id.fabhelp);
        bottomHelp = findViewById(R.id.Bottom_App_Bar_Help);
        bottomHelp.replaceMenu(R.menu.app_bar_menu);
        about2 = findViewById(R.id.bottomabout);
        appbar = findViewById(R.id.button);
        ListVer = findViewById(R.id.button1);
        ListHor = findViewById(R.id.button2);
        search = findViewById(R.id.button3);

        appbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                Intent intent = new Intent(App_Help.this, Help_Instructions.class);
                extras.putString("HelpName","AppBar");
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        ListVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                Intent intent = new Intent(App_Help.this, Help_Instructions.class);
                extras.putString("HelpName","Using Call Directory List");
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        ListHor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                Intent intent = new Intent(App_Help.this, Help_Instructions.class);
                extras.putString("HelpName","Using Map List");
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                Intent intent = new Intent(App_Help.this, Help_Instructions.class);
                extras.putString("HelpName","What Can You Search?");
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        fltbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager manager2= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!manager2.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    fltbtn2.setImageResource(R.drawable.gpsoffstyle);
                }
                else {
                    fltbtn2.setImageResource(R.drawable.gps_iconstyle);
                }
                Intent locsrc = new Intent(App_Help.this, MapActivity.class);
                startActivity(locsrc);
            }

        });

        bottomHelp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager manager2= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!manager2.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    fltbtn2.setImageResource(R.drawable.gpsoffstyle);
                }
                else {
                    fltbtn2.setImageResource(R.drawable.gps_iconstyle);
                }
                Intent gohome = new Intent(App_Help.this,App_Home.class);
                startActivity(gohome);
            }
        });
        bottomHelp.setOverflowIcon(ContextCompat.getDrawable(this,R.drawable.ic_more_vert_24));

        bottomHelp.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.call:
                        LocationManager manager2= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        if (!manager2.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            fltbtn2.setImageResource(R.drawable.gpsoffstyle);
                        }
                        else {
                            fltbtn2.setImageResource(R.drawable.gps_iconstyle);
                        }
                        Intent goCall = new Intent(App_Help.this,App_CallDirectory.class);
                        startActivity(goCall);
                        break;
                    case R.id.firstaid:
                        LocationManager manager3= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        if (!manager3.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            fltbtn2.setImageResource(R.drawable.gpsoffstyle);
                        }
                        else {
                            fltbtn2.setImageResource(R.drawable.gps_iconstyle);
                        }
                        Intent goFirstAid = new Intent(App_Help.this,App_First_Aid.class);
                        startActivity(goFirstAid);
                        break;
                    case R.id.bottomabout:
                        LocationManager manager4= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        if (!manager4.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            fltbtn2.setImageResource(R.drawable.gpsoffstyle);
                        }
                        else {
                            fltbtn2.setImageResource(R.drawable.gps_iconstyle);
                        }
                        Intent goAbout = new Intent(App_Help.this, App_About.class);
                        startActivity(goAbout);
                        break;
                    case R.id.bottomhelp:
                        LocationManager manager5= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        if (!manager5.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            fltbtn2.setImageResource(R.drawable.gpsoffstyle);
                        }
                        else {
                            fltbtn2.setImageResource(R.drawable.gps_iconstyle);
                        }
                        Toast.makeText(App_Help.this,"Already In Help Screen!", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

    }
}