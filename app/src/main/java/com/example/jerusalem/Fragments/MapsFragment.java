package com.example.jerusalem.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jerusalem.R;
import com.example.jerusalem.Adapters.CountryAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MapsFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View view;

    ArrayList<com.example.jerusalem.Models.Country> latAndLong;

    RequestQueue mQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_maps, container, false);

        mQueue = Volley.newRequestQueue(getActivity());
        latAndLong = new ArrayList<>();

        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = view.findViewById(R.id.mapsView);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        jsonParesByCountry(googleMap);
    }


    private  void jsonParesByCountry(final GoogleMap googleMap){
        String url_statsByCountry = "https://disease.sh/v2/countries";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url_statsByCountry, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length() ; i++) {
                        JSONObject object =  response.getJSONObject(i);
                        JSONObject countryInfo = (JSONObject) object.get("countryInfo");
                        Log.d("Json" ,">>>>>>>>>>> lat  :   " +   countryInfo.get("lat"));
                        Log.d("Json" ,">>>>>>>>>>> long :  " +   countryInfo.get("long"));
                        Log.d("Json" ,">>>>>>>>>>> " + object.get("cases"));

                        latAndLong.add(new Country(countryInfo.get("lat").toString(),countryInfo.get("long").toString(),object.get("country").toString(),object.get("cases").toString() ,countryInfo.getString("flag").toString() ,  object.get("recovered").toString() ,object.get("deaths").toString()));

                       // latAndLong.add(new Country(countryInfo.get("lat").toString(),countryInfo.get("long").toString(),object.get("country").toString(),object.get("cases").toString() ,object.getString("flag").toString()));

                    }
                    if(latAndLong.size() != 0){
                        for (int j = 0; j <latAndLong.size() ; j++) {
                            googleMap.addMarker(new MarkerOptions().position(new LatLng(Float.parseFloat(latAndLong.get(j).getLatCountry())  , Float.parseFloat(latAndLong.get(j).getLongCountry()))).title(latAndLong.get(j).getCountryName()).snippet("Cases : " + latAndLong.get(j).getTotalCases()));
                        }
                    }else {
                        Log.d("Json" , "No Country");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Json" , "Error By Country : "+ error.getMessage());
            }
        });
        mQueue.add(request);
    }
}
