package bluefalcons.mapquizapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mongodb.util.JSON;

import static java.lang.Math.abs;

import bluefalcons.mapquizapp.NetworkLayer.AppNavigationNetwork;
import bluefalcons.mapquizapp.NetworkLayer.ServerConnection;

public class AppNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentLocationMarker;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 10000;
    double latitude, longitude;
    private Marker selectedMarker;
    private Quizzes[] mLoadedQuizzes;

    private User uUser = null;
    private String uUserDoc = null;
    private Quizzes uQuiz = null;
    private String uQuizDoc = null;

    public ServerConnection mServer;
    public AppNavigationNetwork mAppNavNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_navigation);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Get incoming user bundle from last activity if it exists
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            uUserDoc = bundle.getString("moved_user");
            if(uUserDoc!=null) {
                uUser = JavaJsonConverter.ConvertJsonToJavaUser(uUserDoc);
                Log.d("Quiz Doc transferred: ", uUserDoc);
                Log.d("User transferred: ", uUser.toString());
            }
        }

        final Button bTakeQuiz = (Button)findViewById(R.id.bTakeQuiz);
        bTakeQuiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(selectedMarker != null)
                {
                    String quizTitle = selectedMarker.getTitle();
                    LatLng quizPosition = selectedMarker.getPosition();
                    double quizLat = quizPosition.latitude;
                    double quizLon = quizPosition.longitude;
                    selectedMarker.remove();
                    selectedMarker = null;
                    findViewById(R.id.bTakeQuiz).setVisibility(View.INVISIBLE);
                    Log.i("Clicked Marker Title", quizTitle);
                    Log.i("Acquired Latitude ", Double.toString(quizLat));
                    Log.i("Acquired Longitude ", Double.toString(quizLon));


                    uQuiz = null;
                    //TODO Get quiz from db (probably by title (quizTitle))
                    //
                    for (Quizzes LoadedQuiz : mLoadedQuizzes) {
                        if (LoadedQuiz.latitude == quizPosition.latitude && LoadedQuiz.longitude == quizPosition.longitude) {
                            uQuiz = LoadedQuiz;
                            break;
                        }
                    }
                    if(uUser != null && uQuiz != null)
                    {
                        Intent intent = new Intent(AppNavigation.this, Quiz1Activity.class);
                        uUserDoc = JavaJsonConverter.ConvertUserObjectToJson(uUser);
                        intent.putExtra("moved_user", uUserDoc);
                        uQuizDoc = JavaJsonConverter.ConvertQuizObjectToJson(uQuiz);
                        intent.putExtra("moved_quiz", uQuizDoc);
                        startActivity(intent);
                    }
                    else
                    {
                        //Possibly give some notification
                    }

                }
                else
                {
                    findViewById(R.id.bTakeQuiz).setVisibility(View.INVISIBLE);
                    Log.i("Note", "No marker was selected");
                }
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Finding Nearby Locations", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                mMap.clear();
                //
                mAppNavNet.PingQuizzes(latitude, longitude);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*
         * Initializes Connection to server and passes socket to network controller for the App Navigation screen.
         */
        mServer = ServerConnection.getInstance();
        mAppNavNet = AppNavigationNetwork.getInstance(this);
        mAppNavNet.SetSocket(mServer.GetSocket());
        mAppNavNet.SetupSocketListeners();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_navigation, menu);

        if(uUser != null)
        {
            TextView uNameText = ((TextView)findViewById(R.id.tvBarUsername));
            uNameText.setText(uUser.GetUsername());
            TextView uScoreText = ((TextView)findViewById(R.id.tvBarScore));
            uScoreText.setText(("Score: " + String.valueOf(uUser.GetScore())));
        }
        else
        {
            TextView uNameText = ((TextView)findViewById(R.id.tvBarUsername));
            uNameText.setText("Not Logged In.");
            TextView uScoreText = ((TextView)findViewById(R.id.tvBarScore));
            uScoreText.setText("");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            if(uUser == null)
            {
                Intent intent = new Intent(AppNavigation.this, LoginActivity.class);
                startActivity(intent);
            }
            else
            {
                uUser = null;
                TextView uNameText = ((TextView)findViewById(R.id.tvBarUsername));
                uNameText.setText("Not Logged In");
                TextView uScoreText = ((TextView)findViewById(R.id.tvBarScore));
                uScoreText.setText("");
                item.setTitle("Login/Register");
            }
        } else if (id == R.id.nav_gallery) {
            //Just here to test methods
            if(uUser != null && uUser.GetUserID().equals("admin"))
            {
                Intent intent = new Intent(AppNavigation.this, QuizCreator.class);
                uUserDoc = JavaJsonConverter.ConvertUserObjectToJson(uUser);
                intent.putExtra("moved_user", uUserDoc);
                startActivity(intent);
            }

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(AppNavigation.this, ScoresActivity.class);
            if (uUser != null) {
                uUserDoc = JavaJsonConverter.ConvertUserObjectToJson(uUser);
                intent.putExtra("moved_user", uUserDoc);
            }
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Runs once map activity is opened and map fragment creation is detected
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else
        {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        //Set functionality for when a quiz marker is clicked
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                selectedMarker = marker;
                String quizTitle = marker.getTitle();
                LatLng quizPosition = marker.getPosition();
                double quizLat = quizPosition.latitude;
                double quizLon = quizPosition.longitude;
                marker.showInfoWindow();

                Log.i("Clicked Marker Title" , quizTitle);
                Log.i("Acquired Latitude " , Double.toString(quizLat));
                Log.i("Acquired Longitude " , Double.toString(quizLon));

                findViewById(R.id.bTakeQuiz).setVisibility(View.VISIBLE);
                return true;
            }
        });

    }

    protected synchronized void buildGoogleApiClient()
    {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }

    @Override
    public void onLocationChanged(Location location)
    {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        lastLocation = location;

        if(currentLocationMarker != null)
        {
            currentLocationMarker.remove();
        }
        Log.d("lat = ",""+latitude);
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if(client != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }

    public boolean checkLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            return false;
        }
        else
            return true;
    }

    @Override
    public void onConnectionSuspended(int i)
    {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {

    }

    public void PingResponse(String quizzes){
        Log.d("RESPONSE", quizzes.toString());
        mLoadedQuizzes = JavaJsonConverter.ConvertPingResponse(quizzes);
        View currentView = this.getCurrentFocus();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(currentView, "Found Locations!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                for(int i=0; i<mLoadedQuizzes.length; i++){
                    Log.d("QUIZ OBJ", mLoadedQuizzes[i].title);
                    PingQuizzesOnMap(JavaJsonConverter.ConvertQuizObjectToJson(mLoadedQuizzes[i]));
                }
            }
        });
    }

    public void PingQuizzesOnMap(String quiz) {
        if (quiz != null) {

            Quizzes quizObj = JavaJsonConverter.ConvertJsonToJavaQuiz(quiz);
            Log.i("Final Output: ", quiz.toString());

            String markerTitle = quizObj.title;
            String markerInfo = quizObj.info;

            double lat = quizObj.latitude;
            double lon = quizObj.longitude;

            LatLng latLng = new LatLng(lat, lon);
            if (abs(lat - latitude) <= 0.06 && abs(lon - longitude) <= 0.06) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title(markerTitle);
                markerOptions.snippet(markerInfo);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                markerOptions.position(latLng);
                Marker markerName = mMap.addMarker(markerOptions);
            }
        }
    }
}