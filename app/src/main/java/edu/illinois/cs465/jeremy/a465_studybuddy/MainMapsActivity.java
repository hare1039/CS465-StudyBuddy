package edu.illinois.cs465.jeremy.a465_studybuddy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;


import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.maps.android.ui.IconGenerator;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.view.View.*;
import static com.google.maps.android.ui.IconGenerator.STYLE_BLUE;
import static com.google.maps.android.ui.IconGenerator.STYLE_DEFAULT;

public class MainMapsActivity extends FragmentActivity implements OnMapReadyCallback,
OnInfoWindowClickListener {

    private GoogleMap mMap;
    private BottomNavigationView bottomNav;
    private FloatingActionButton fab;

    // Used to control marker interaction
    private Marker start_pos;
    private Marker[] other_marks = new Marker[5];


    // Starting location
    private LatLng grainger = new LatLng(40.112445,-88.226792);
    private LatLng union = new LatLng(40.1092101,-88.2272225);
    private boolean first_move = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        bottomNav = (BottomNavigationView) findViewById(R.id.main_menu_nav);
        fab = (FloatingActionButton) findViewById(R.id.loc_fab);






        bottomNav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.people_list:
                                // TODO
                                startActivity(new Intent(MainMapsActivity.this, PeoReqTabs.class));
                                return true;
                            case R.id.requests_list:
                                // TODO
                                startActivity(new Intent(MainMapsActivity.this, SubmitRequestActivity.class));
                                return true;
                            case R.id.my_profile:
                                // TODO
                                startActivity(new Intent(MainMapsActivity.this, MainProfileActivity.class));
                                return true;
                            case R.id.messages:
                                //TODO
                                startActivity(new Intent(MainMapsActivity.this, SelectMessageActivity.class));
                                return true;
                        }
                        return false;
                    }
                });



                bottomNavSettings(bottomNav);
    }





    private void bottomNavSettings(BottomNavigationView bottomNav) {
        bottomNav.setItemHorizontalTranslationEnabled(true);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("RestrictedApi")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);
        GoogleMapOptions opt = new GoogleMapOptions();
        opt.zoomGesturesEnabled(true);
        // Add a marker in Grainger and move the camera
        final float zoom = 16.0f;

        start_pos = mMap.addMarker(new MarkerOptions()
                .position(grainger)
                .title("3 Users Nearby"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(grainger, zoom));
        final CameraUpdate start = CameraUpdateFactory.newLatLngZoom(grainger, zoom);

        Circle startRad = googleMap.addCircle(new CircleOptions()
        .center(grainger)
        .radius(50.0f));
        startRad.setVisible(false);


        LatLng new_marker = getRandomLocation(grainger, 150);
        other_marks[0] = mMap.addMarker( new MarkerOptions().position(new_marker)
                .title("User Here"));
        IconGenerator igen = new IconGenerator(this);
        igen.setStyle(STYLE_BLUE);
        Bitmap icon = igen.makeIcon("1");
        other_marks[0].setIcon(BitmapDescriptorFactory.fromBitmap(icon));
        other_marks[0].setSnippet("Susan Ann");

        float darker_green = 105.0f;
        for (int i = 1; i < 5; i++) {
            new_marker = getRandomLocation(grainger, 325);

            if (i == 4) {
                other_marks[4] = mMap.addMarker(new MarkerOptions().position(union).title("Many Users Here"));
                icon = igen.makeIcon("10+");
                other_marks[4].setIcon(BitmapDescriptorFactory.fromBitmap(icon));
                break;
            }

            other_marks[i] = mMap.addMarker( new MarkerOptions().position(new_marker)
            .title("User Here"));
            //.icon(getMarkerIcon("#1b7801")));



            Random random = new Random();
            String people_here = String.valueOf(random.ints(1,  6).findFirst().getAsInt());
            if (!people_here.equals("1")) {
                other_marks[i].setSnippet("Users Here");
            }
            icon = igen.makeIcon(people_here);
            other_marks[i].setIcon(BitmapDescriptorFactory.fromBitmap(icon));

        }


        // Try to draw a badge number for icon
        /*Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(16.0f);
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(200, 50, conf);
        Canvas canvas = new Canvas(bmp);

        canvas.drawText("TEXT", 0, 50, paint); // paint defines the text color, stroke width, size*/




        //mMap.animateCamera(CameraUpdateFactory.zoomTo(zoom));

        //fab.setVisibility(View.INVISIBLE);
        mMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                fab.show();
            }
        });

        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                //Toast.makeText(MainMapsActivity.this, String.valueOf(first_move),Toast.LENGTH_SHORT).show();



                /*if (first_move)
                    first_move = false;
                if (!first_move)
                    fab.show();
                */

            }
        });

        fab.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                // Handle the click.
                mMap.animateCamera(start);
                fab.setVisibility(View.INVISIBLE);
                fab.hide();

            }
        });
    }



    // method definition
    public BitmapDescriptor getMarkerIcon(String color) {
        float[] hsv = new float[3];
        Color.colorToHSV(Color.parseColor(color), hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }

    public LatLng getRandomLocation(LatLng point, int radius) {

        //List<LatLng> randomPoints = new ArrayList<>();
        //List<Float> randomDistances = new ArrayList<>();

        Location myLocation = new Location("");
        myLocation.setLatitude(point.latitude);
        myLocation.setLongitude(point.longitude);

        double x0 = point.latitude;
        double y0 = point.longitude;

        Random random = new Random();

        // Convert radius from meters to degrees
        double radiusInDegrees = radius / 111000f;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(y0);

        double foundLatitude = new_x + x0;
        double foundLongitude = y + y0;
        LatLng randomLatLng = new LatLng(foundLatitude, foundLongitude);


       /* randomPoints.add(randomLatLng);
        Location l1 = new Location("");
        l1.setLatitude(randomLatLng.latitude);
        l1.setLongitude(randomLatLng.longitude);
        randomDistances.add(l1.distanceTo(myLocation));*/

        return randomLatLng;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.equals(start_pos)  || marker.equals(other_marks[4])) {
            startActivity(new Intent(MainMapsActivity.this, PeoReqTabs.class));
        }
        if(marker.equals(other_marks[0])) {
            startActivity(new Intent(MainMapsActivity.this, OtherProfileActivity.class));
        }

    }
}
