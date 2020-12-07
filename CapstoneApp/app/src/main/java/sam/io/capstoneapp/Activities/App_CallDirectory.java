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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sam.io.capstoneapp.Adapters.CallDirectoryAdapter;
import sam.io.capstoneapp.Models.CallDirectoryItems;
import sam.io.capstoneapp.R;

public class App_CallDirectory extends AppCompatActivity {
    private static final int REQUEST_CALL=1;
    private static String JSON_URL = "https://capstone-project-2020.000webhostapp.com/api/product/calldirectory.php";
    RecyclerView recyclerView;
    private List<CallDirectoryItems> items;
    CallDirectoryAdapter adapter;
    private Toolbar toptoolbarcall;
    private BottomAppBar bottom;
    FloatingActionButton fltbtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app__call_directory);
        //FINDING BY IDS AND ANIMATIONS
        bottom = findViewById(R.id.Bottom_App_Bar_Call);
        bottom.replaceMenu(R.menu.app_bar_menu);

        toptoolbarcall = findViewById(R.id.toolbar);
        setSupportActionBar(toptoolbarcall);

        getSupportActionBar().setTitle("Call Directory");
        fltbtn2 = findViewById(R.id.FAB);
        // GO TO LOCATION SEARCH SCREEN
        fltbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locsrc = new Intent(App_CallDirectory.this, MapActivity.class);
                startActivity(locsrc);
            }

        });
        bottom.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RETURN TO HOME SCREEN
                Intent backhome = new Intent(App_CallDirectory.this,App_Home.class);
                startActivity(backhome);
            }
        });
        bottom.setOverflowIcon(ContextCompat.getDrawable(this,R.drawable.ic_more_vert_24));

        bottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.call:
                        Toast.makeText(App_CallDirectory.this,"Already in call Screen!",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.firstaid:
                        Intent goFirstAid = new Intent(App_CallDirectory.this,App_First_Aid.class);
                        startActivity(goFirstAid);
                        break;
                    case R.id.bottomabout:
                        Intent goabout = new Intent(App_CallDirectory.this, App_About.class);
                        startActivity(goabout);
                        break;
                    case R.id.bottomhelp:
                        Intent goHelp = new Intent(App_CallDirectory.this,App_Help.class);
                        startActivity(goHelp);
                        break;
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.call_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LocationManager manager2= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // CHECK GPS FUNCTION
        if (!manager2.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            fltbtn2.setImageResource(R.drawable.gpsoffstyle);
        }
        else {
            fltbtn2.setImageResource(R.drawable.gps_iconstyle);
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0 && !fltbtn2.isShown())
                    fltbtn2.show();
                else if(dy > 0 && fltbtn2.isShown())
                    fltbtn2.hide();
            }
        });
        // ************* END OF FINDING BY IDS AND ANIMATIONS ******************
        items = new ArrayList<>();

        checkConnection();
        extractItems();
    }
        private void extractItems(){
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject itemObject = response.getJSONObject(i);
                            CallDirectoryItems itm = new CallDirectoryItems();
                            itm.setCont_Name(itemObject.getString("Contact_Name").toString());
                            itm.setCont_Number(itemObject.getString("Contact_Number").toString());
                            itm.setCont_Image(itemObject.getString("Contact_Image").toString());
                            itm.setCat_Image(itemObject.getString("Category_Icon").toString());
                            itm.setCat_Name(itemObject.getString("Category_Name"));
                            itm.setCont_Address(itemObject.getString("Contact_Address"));
                            itm.setCont_Affiliation(itemObject.getString("Affiliation"));
                            itm.setCont_Services(itemObject.getString("Services"));
                            itm.setCont_Hmo(itemObject.getString("HMO"));
                            itm.setCont_Offers(itemObject.getString("Offers"));
                            itm.setCont_Service_Sched(itemObject.getString("Service_Schedule"));
                            items.add(itm);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new CallDirectoryAdapter(getApplicationContext(),items);
                    recyclerView.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("tag", "onErrorResponse: " + error.getMessage());
                }
            });
            queue.add(jsonArrayRequest);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_toolbar,menu);

        MenuItem searchitem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchitem.getActionView();
        searchView.setQueryHint("Ex. Hospital / Radiology");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    public void checkConnection(){
        if(isOnline()){
            Toast.makeText(App_CallDirectory.this, "Connected", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(App_CallDirectory.this, "Cannot Load Call Directory, Please Check your Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }


    //@Override
    //public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       // switch (item.getItemId()){
        //    case R.id.Help:
             //   Toast.makeText(this, "Clicked Help!", Toast.LENGTH_SHORT).show();
            //    return true;
           // case R.id.About:
           //     Toast.makeText(this,"Clicked About!",Toast.LENGTH_SHORT).show();
           // default:
            //    return super.onOptionsItemSelected(item);
       // }
   // }

}



