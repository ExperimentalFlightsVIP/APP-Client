package com.example.tejasvedantham.dronedelivery;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.util.ArrayList;

public class MapsActivity extends Fragment {

    //MapView mMapView;
    private GoogleMap googleMap;
    private SupportMapFragment mSupportMapFragment;
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
        Log.d("MyApp", "root view inflated: " + rootView );
        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map, mSupportMapFragment).commit();
        }

        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    if (googleMap != null) {
                        Requests req = new Requests(MapsActivity.this, googleMap);
                        req.execute();
                        googleMap.getUiSettings().setAllGesturesEnabled(true);
                        LatLng techTower = new LatLng(33.772347, -84.394706);
                        googleMap.addMarker(new MarkerOptions().position(techTower).title("Tech Tower").snippet("The Famous GT Landmark!"));
                        LatLng eastRes = new LatLng(33.772443, -84.391807);
                        googleMap.addMarker(new MarkerOptions().position(eastRes).title("East Residential Delivery Station").snippet("The first delivery station!"));
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(techTower).zoom(17.0f).build();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                        googleMap.moveCamera(cameraUpdate);

                        GroundOverlayOptions drone = new GroundOverlayOptions()
                                .image(BitmapDescriptorFactory.fromResource(R.drawable.droneicon))
                                .position(techTower, 30.0f);
                        googleMap.addGroundOverlay(drone);
                    }

                }
            });
        }
        return rootView;
    }
}
