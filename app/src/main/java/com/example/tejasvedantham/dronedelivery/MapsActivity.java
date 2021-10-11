package com.example.tejasvedantham.dronedelivery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
//    Tried to set a marker with the onCreate Method. Didn't work.
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        onMapReady(googleMap);
//        LatLng techTower = new LatLng(33.77, -84.39);
//        googleMap.addMarker(new MarkerOptions().position(techTower).title("Tech Tower").snippet("The Famous GT Landmark!"));
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing user location
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng techTower = new LatLng(33.77, -84.39);
                googleMap.addMarker(new MarkerOptions().position(techTower).title("Tech Tower").snippet("The Famous GT Landmark!"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(techTower).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}





