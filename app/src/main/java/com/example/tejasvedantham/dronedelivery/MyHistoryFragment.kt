package com.example.tejasvedantham.dronedelivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MyHistoryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //TextView textView = root.findViewById(R.id.text_home);
        //textView.setText("This is a test of home page");
        return inflater.inflate(R.layout.fragment_my_history, container, false)
    }
}