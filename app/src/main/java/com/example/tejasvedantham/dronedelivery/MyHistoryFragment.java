package com.example.tejasvedantham.dronedelivery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tejasvedantham.dronedelivery.R;

public class MyHistoryFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_history, container, false);

        //TextView textView = root.findViewById(R.id.text_home);
        //textView.setText("This is a test of home page");


        return root;
    }
}
