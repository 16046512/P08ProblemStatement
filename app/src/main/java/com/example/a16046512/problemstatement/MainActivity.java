package com.example.a16046512.problemstatement;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button btn1, btn2, btn3;
    Spinner spinner;
    private GoogleMap map;
    LatLng amk,jurong,Singapore,Admiralty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);


        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;


                 Singapore = new LatLng(1.4381922, 103.78895970000008);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(Singapore,
                        11));


                 Admiralty = new LatLng(1.4381922, 103.78895970000008);
                Marker AdmiraltyMarker = map.addMarker(new
                        MarkerOptions()
                        .position(Admiralty)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave3,765764")
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.star)));

                 amk = new LatLng(1.3691149, 103.8454342);
                Marker amkmarker = map.addMarker(new
                        MarkerOptions()
                        .position(amk)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                 jurong = new LatLng(1.3328572, 103.74355220000007);
                Marker jurongmarker = map.addMarker(new
                        MarkerOptions()
                        .position(jurong)
                        .title("East")
                        .snippet("Block 555, Tempines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));



                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(MainActivity.this,marker.getTitle(),Toast.LENGTH_LONG).show();
                        return false;
                    }
                });
            }
        });

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(Admiralty,
                            15));
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(amk,
                            15));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(jurong,
                            15));

                }
            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {

            Log.i("ttt",spinner.getItemAtPosition(pos)+"");
        if (spinner.getItemAtPosition(pos).equals("NORTH")){

            if (map != null) {
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(Admiralty,
                        15));
            }

        }else if(spinner.getItemAtPosition(pos).equals("CENTRAL")){

            if (map != null){
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(amk,
                        15));
            }
        }else if(spinner.getItemAtPosition(pos).equals("EAST")){
            if (map != null){
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(jurong,
                        15));

            }
        }else{
            Log.i("ttt","noselect");


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
