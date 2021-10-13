package com.example.tejasvedantham.dronedelivery;

import android.graphics.Color;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.*;

public class Requests extends AsyncTask<Void, Void, ArrayList<LatLng>> {

    private static final String server = "https://37ecdb42-04ea-4fa5-bf5f-f14c790b5749.mock.pstmn.io/";

    private static MapsActivity activity;
    private static GoogleMap googleMap;

    public Requests(MapsActivity activity, GoogleMap googleMap) {
        this.activity = activity;
        this.googleMap = googleMap;
    }

    @Override
    protected ArrayList<LatLng> doInBackground(Void... params) {
        try {
            URL url = new URL(server + "getDronePath1");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            System.out.println(con.getResponseCode());
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println(response);
                return parseCoords(response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<LatLng>();
    }

    private static ArrayList<LatLng> parseCoords(String r) throws JSONException {
        JSONArray arr = new JSONArray(r);
        double[][] coords = new double[arr.length()][2];
        for (int i = 0; i < arr.length(); i++) {
            coords[i][0] = arr.getJSONArray(i).getDouble(0);
            coords[i][1] = arr.getJSONArray(i).getDouble(1);
        }
        ArrayList<LatLng> list = new ArrayList<>(coords.length);
        for (int i = 0; i < coords.length; i++) {
            list.add(new LatLng(coords[i][0], coords[i][1]));
        }
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<LatLng> route) {
        Polyline testRoute = googleMap.addPolyline(new PolylineOptions().addAll(route));
        testRoute.setStartCap(new RoundCap());
        testRoute.setEndCap(new RoundCap());
        testRoute.setColor(Color.rgb(179, 163, 105));
    }
}
