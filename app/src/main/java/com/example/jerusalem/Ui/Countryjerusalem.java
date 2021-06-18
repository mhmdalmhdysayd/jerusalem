package com.example.jerusalem.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cov19.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.URI;
import java.net.URL;

public class Countryjerusalem extends AppCompatActivity implements OnMapReadyCallback {
    ImageView imageCountery;
    TextView nameCountery , totalCountery , recoveredCountery , deathsCountery  ;

    GoogleMap mGoogleMap;
    MapView mMapView;

    String name ,lat ,longC , image , total , deaths ,recovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_cov19);

       Intent intent = getIntent();
        name =  intent.getStringExtra("nameCountery");
        lat = intent.getStringExtra("latCountery");
        longC =  intent.getStringExtra("longCountery");
        image = intent.getStringExtra("imageCountery");
        total = intent.getStringExtra("totalCasesOnCountery");
        deaths = intent.getStringExtra("deaths");
        recovered = intent.getStringExtra("recovered");

        nameCountery = findViewById(R.id.nameCountery);
        imageCountery =  findViewById(R.id.imageCountery);
        totalCountery =  findViewById(R.id.totalCountery);
        recoveredCountery = findViewById(R.id.recoveredCountery);
        deathsCountery =findViewById(R.id.deathsCountery);

        deathsCountery.setText(deaths);
        recoveredCountery.setText(recovered);
        totalCountery.setText(total);
        nameCountery.setText(name);
       // Glide.with(getApplicationContext()).load(image).into(imageCountery);
        Log.d("CounteryCove" , name + lat + longC + image + total);

        mMapView = findViewById(R.id.mapView);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        googleMap.addMarker(new MarkerOptions().position(new LatLng(Float.parseFloat(lat)  , Float.parseFloat(longC))).title(name).snippet("Cases : " + total));

        LatLng latLng = new LatLng(Float.parseFloat(lat) , Float.parseFloat(longC));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(2));


    }
}
