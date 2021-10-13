package com.example.tejasvedantham.dronedelivery;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.*;

public class Requests {

    private static final String server = "https://37ecdb42-04ea-4fa5-bf5f-f14c790b5749.mock.pstmn.io/";

    public static double[][] getNewPath() throws IOException, JSONException {
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
        return new double[0][0];
    }

    private static double[][] parseCoords(String r) throws JSONException {
        JSONArray arr = new JSONArray(r);
        double[][] coords = new double[arr.length()][2];
        for (int i = 0; i < arr.length(); i++) {
            coords[i][0] = arr.getJSONArray(i).getDouble(0);
            coords[i][1] = arr.getJSONArray(i).getDouble(1);
        }
        return coords;
    }

    public static ArrayList<LatLng> requestRoute() throws IOException, JSONException {
        double[][] coords = Requests.getNewPath();
        ArrayList<LatLng> list = new ArrayList<>(coords.length);
        for (int i = 0; i < coords.length; i++) {
            list.add(new LatLng(coords[i][0], coords[i][1]));
        }
        return list;
    }
}

