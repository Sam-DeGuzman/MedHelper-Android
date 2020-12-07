package sam.io.capstoneapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

//MAPBOX classes
// classes needed to initialize map
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

// classes needed to add the location component
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;

// classes needed to add a marker
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;

// classes to calculate a route
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sam.io.capstoneapp.Adapters.CustomAdaptor;
import sam.io.capstoneapp.LinearLayoutManagerWithSmoothScroller;
import sam.io.capstoneapp.Models.App;
import sam.io.capstoneapp.R;

import android.util.Log;

// classes needed to launch navigation UI
import android.widget.Button;

import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MapActivity extends AppCompatActivity implements CustomAdaptor.SelectedUser, OnMapReadyCallback, PermissionsListener {
    // variables for adding location layer
    private MapView mapView;
    private MapboxMap mapboxMap;

    // variables for adding location layer
    private PermissionsManager permissionsManager;
    private LocationComponent locationComponent;

    // variables for calculating and drawing a route
    private DirectionsRoute currentRoute;
    private static final String TAG = "DirectionsActivity";
    private NavigationMapRoute navigationMapRoute;

    // variables needed to initialize navigation
    private FloatingActionButton directionbutton;
    private Button marker;
    private ImageButton backmap;
    double LONGITUDE = 121.09246730804445, LATITUDE = 14.623264342719715;

    // Recycler View object
    RecyclerView mList1;

    List<App> appList;
    private String JSON_URL = "https://capstone-project-2020.000webhostapp.com/api/product/location.php";
    private CustomAdaptor adaptor1;

    private Toolbar toptoolbarmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        statusCheck();
        setContentView(R.layout.activity_map);

        mList1 = findViewById(R.id.list1);

        appList = new ArrayList<>();

        loadInfos();
        checkConnection();
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


        toptoolbarmap = findViewById(R.id.toolbar);
        setSupportActionBar(toptoolbarmap);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Location Search");
        toptoolbarmap.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        LinearLayoutManager manager1 = new LinearLayoutManager(this);
//        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList1.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(this));

    }

    private void loadInfos(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray infos = new JSONArray(response);

                    for (int i = 0; i < infos.length(); i++){
                        JSONObject infoObject = infos.getJSONObject(i);

                        String name = infoObject.getString("Contact_Name");
                        String category = infoObject.getString("Category_Name");
                        String address = infoObject.getString("Contact_Address");
                        String hour = infoObject.getString("Service_Schedule");
                        String phone = infoObject.getString("Contact_Number");
                        String affiliation = infoObject.getString("Affiliation");
                        String services = infoObject.getString("Services");
                        String hmo = infoObject.getString("HMO");
                        String offers = infoObject.getString("Offers");
                        double lat = infoObject.getDouble("Location_Latitude");
                        double longi = infoObject.getDouble("Location_Longitude");

                        App app = new App(name,category,address,hour.replaceAll(",","\n"
                        ),phone,lat,longi,affiliation,services,hmo,offers);

                        appList.add(app);
                    }
                    adaptor1 = new CustomAdaptor(MapActivity.this,appList,MapActivity.this);
                    mList1.setAdapter(adaptor1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MapActivity.this,"Successful",Toast.LENGTH_SHORT).show();


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MapActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }


    @Override
    public void selectedUser(App app) {
//        Toast toast = Toast.makeText(this,"Latitude: "+app.getLatitude()+"Longitude: "+app.getLongitude(),Toast.LENGTH_SHORT);
////        toast.show();
        Point point = Point.fromLngLat(app.getLongitude(),app.getLatitude());
        LatLng latLng = new LatLng(point.latitude(), point.longitude());
        Point destinationPoint = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());

        Point originPoint = Point.fromLngLat(locationComponent.getLastKnownLocation().getLongitude(),
                locationComponent.getLastKnownLocation().getLatitude());

        GeoJsonSource source = mapboxMap.getStyle().getSourceAs("destination-source-id");
        if (source != null) {
            source.setGeoJson(Feature.fromGeometry(destinationPoint));
        }
        getRoute(originPoint, destinationPoint);
        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(latLng.getLatitude(),latLng.getLongitude())) // Sets the new camera position
                .zoom(13)
                .bearing(180) // Rotate the camera
                .tilt(30) // Set the camera tilt
                .build(); // Creates a CameraPosition from the builder
        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position),7000);
        directionbutton.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                adaptor1.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        mapboxMap.setStyle(getString(R.string.navigation_guidance_day), new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                enableLocationComponent(style);
                addDestinationIconSymbolLayer(style);

                directionbutton = findViewById(R.id.directionbutton);

                directionbutton.hide();

//                marker = findViewById(R.id.marker);
//                marker.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v ) {
//                        Point point = Point.fromLngLat(LONGITUDE,LATITUDE);
//                        LatLng latLng = new LatLng(point.latitude(), point.longitude());
//                        Point destinationPoint = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());
//
//                        Point originPoint = Point.fromLngLat(locationComponent.getLastKnownLocation().getLongitude(),
//                                locationComponent.getLastKnownLocation().getLatitude());
//
//                        GeoJsonSource source = mapboxMap.getStyle().getSourceAs("destination-source-id");
//                        if (source != null) {
//                            source.setGeoJson(Feature.fromGeometry(destinationPoint));
//                        }
//
//                        getRoute(originPoint, destinationPoint);
//                        CameraPosition position = new CameraPosition.Builder()
//                                .target(new LatLng(latLng.getLatitude(),latLng.getLongitude())) // Sets the new camera position
//                                .zoom(13)
//                                .bearing(180) // Rotate the camera
//                                .tilt(30) // Set the camera tilt
//                                .build(); // Creates a CameraPosition from the builder
//                        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position),7000);
//                        directionbutton.show();
//                    }
//                });
                directionbutton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                                .directionsRoute(currentRoute)
                                .shouldSimulateRoute(false)
                                .build();
// Call this method with Context from within an Activity
                        NavigationLauncher.startNavigation(MapActivity.this, options);
                    }
                });
            }
        });
    }

    private void addDestinationIconSymbolLayer(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addImage("destination-icon-id",
                BitmapFactory.decodeResource(this.getResources(), R.drawable.mapbox_marker_icon_default));
        GeoJsonSource geoJsonSource = new GeoJsonSource("destination-source-id");
        loadedMapStyle.addSource(geoJsonSource);
        SymbolLayer destinationSymbolLayer = new SymbolLayer("destination-symbol-layer-id", "destination-source-id");
        destinationSymbolLayer.withProperties(
                iconImage("destination-icon-id"),
                iconAllowOverlap(true),
                iconIgnorePlacement(true)
        );
        loadedMapStyle.addLayer(destinationSymbolLayer);
    }
    private void getRoute(Point origin, Point destination) {
        NavigationRoute.builder(this)
                .accessToken(Mapbox.getAccessToken())
                .origin(origin)
                .destination(destination)
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
// You can get the generic HTTP info about the response
                        Log.d(TAG, "Response code: " + response.code());
                        if (response.body() == null) {
                            Log.e(TAG, "No routes found, make sure you set the right user and access token.");
                            return;
                        } else if (response.body().routes().size() < 1) {
                            Log.e(TAG, "No routes found");
                            return;
                        }

                        currentRoute = response.body().routes().get(0);
// Draw the route on the map
                        if (navigationMapRoute != null) {
                            navigationMapRoute.removeRoute();
                        } else {
                            navigationMapRoute = new NavigationMapRoute(null, mapView, mapboxMap, R.style.NavigationMapRoute);
                        }
                        navigationMapRoute.addRoute(currentRoute);
                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable.getMessage());
                    }
                });
    }


    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
// Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
// Activate the MapboxMap LocationComponent to show user location
// Adding in LocationComponentOptions is also an optional parameter
            locationComponent = mapboxMap.getLocationComponent();
            locationComponent.activateLocationComponent(this, loadedMapStyle);
            locationComponent.setLocationComponentEnabled(true);
            locationComponent.setRenderMode(RenderMode.COMPASS);
// Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING_GPS);
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocationComponent(mapboxMap.getStyle());
        } else {
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
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

//    private boolean deviceHasInternetConnection() {
//        ConnectivityManager connectivityManager = (ConnectivityManager)
//                getApplicationContext().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
//        return activeNetwork != null && activeNetwork.isConnected();
//    }

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
            Toast.makeText(MapActivity.this, "Connected", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MapActivity.this, "Cannot Load Map Items, Please Check your Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void statusCheck(){
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }


}
