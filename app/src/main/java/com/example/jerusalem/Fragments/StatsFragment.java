package com.example.jerusalem.Fragments;

import android.app.DownloadManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.cov19.R;
import com.example.jerusalem.Adapters.CountryAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class StatsFragment extends Fragment {

    RequestQueue mQueue;
    TextView number , date;
    RecyclerView recyclerView;
    ArrayList<com.example.jerusalem.Models.Country> country;
    CountryAdapter countryAdapter;
    List<DataEntry> data;
    Pie pie;
    AnyChartView anyChartView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_stats, container, false);


         mQueue = Volley.newRequestQueue(getActivity());
        date = view.findViewById(R.id.date);
     //   number = view.findViewById(R.id.number);
        recyclerView = view.findViewById(R.id.recyclerViewStats);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        country = new ArrayList<>();

        jsonPares();
        jsonParesByCountry();
        pie = AnyChart.pie();
        anyChartView = (AnyChartView) view.findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);

        return view;
    }
    private void jsonPares() {
        String url = "https://disease.sh/v2/all";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    data = new ArrayList<>();
                    data.add(new ValueDataEntry("Total cases : " +  response.getString("cases") , Integer.parseInt( response.getString("cases"))));
                    data.add(new ValueDataEntry("Recovered : " + response.getString("recovered"), Integer.parseInt(response.getString("recovered"))));
                    data.add(new ValueDataEntry("Deaths : " + response.getString("deaths"), Integer.parseInt(response.getString("deaths"))));
                    data.add(new ValueDataEntry("Cases_Today : " + response.getString("todayCases"), Integer.parseInt(response.getString("todayCases"))));
                    pie.data(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { //Log.d("Json", error.getMessage());
               }
        });
        mQueue.add(request);
    }

    private  void jsonParesByCountry(){
        String url_statsByCountry = "https://disease.sh/v2/countries";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url_statsByCountry, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length() ; i++) {
                        JSONObject object =  response.getJSONObject(i);
                        JSONObject countryInfo = (JSONObject) object.get("countryInfo");


                        country.add(new Country(countryInfo.get("lat").toString(),countryInfo.get("long").toString(),object.get("country").toString(),object.get("cases").toString() ,countryInfo.getString("flag").toString() ,  object.get("recovered").toString() ,object.get("deaths").toString()));

                        //country.add(new Country(object.get("country").toString(),object.get("cases").toString(),countryInfo.get("flag").toString()));
                        countryAdapter = new CountryAdapter(getContext() , country);
                        recyclerView.setAdapter(countryAdapter); }
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
