package edu.illinois.cs465.jeremy.a465_studybuddy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.view.View.*;

public class MainMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private BottomNavigationView bottomNav;
    private FloatingActionButton fab;

    // Starting location
    private LatLng grainger = new LatLng(40.112445,-88.226792);
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
                                startActivity(new Intent(MainMapsActivity.this, PeoReqTabs.class));
                                return true;
                            case R.id.my_profile:
                                // TODO
                                startActivity(new Intent(MainMapsActivity.this, MainProfileActivity.class));
                                return true;
                            case R.id.messages:
                                //TODO
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

        // Add a marker in Grainger and move the camera
        final float zoom = 17.1f;

        mMap.addMarker(new MarkerOptions().position(grainger).title("5 Users Here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(grainger, zoom));
        final CameraUpdate start = CameraUpdateFactory.newLatLngZoom(grainger, zoom);
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
}
