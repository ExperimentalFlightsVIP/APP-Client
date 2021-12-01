package com.example.tejasvedantham.dronedelivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Order(val station: String? = null, val pkg: String? = null, val gtid: Int? = null) {
    val status = "In Transit"
}

class OrderDroneFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //TextView textView = root.findViewById(R.id.text_home);
        //textView.setText("This is a test of home page");
        val view: View = inflater.inflate(R.layout.fragment_order_drone, container, false)

        // Hook xml items
        val eastButton: RadioButton = view.findViewById(R.id.east_button)
        val centerButton: RadioButton = view.findViewById(R.id.center_button)
        val westButton: RadioButton = view.findViewById(R.id.west_button)
        val packageDropdown: Spinner = view.findViewById(R.id.package_dropdown)
        val gtidEditText: EditText = view.findViewById(R.id.gtid_editText)
        val submitButton: Button = view.findViewById(R.id.submit_order_button)

        // Initialize Firebase database
        //val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://drone-delivery-16436-default-rtdb.firebaseio.com/")
        val firestoredb = Firebase.firestore

        // Populate the package selection dropdown (Spinner) with items. For now, it uses a list of dummy items in the string resources.
        ArrayAdapter.createFromResource(
            this.context!!,
            R.array.packages_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specifies the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            packageDropdown.adapter = adapter
        }

        submitButton.setOnClickListener {
            // A valid order has a delivery station, a package, and a valid GTID

            // Find out which delivery station the user selected
            var selectedStation = "not selected"
            if (eastButton.isChecked) {
                selectedStation = "East Campus"
            } else if (centerButton.isChecked) {
                selectedStation = "Student Center"
            } else if (westButton.isChecked) {
                selectedStation = "West Campus"
            }

            // Find out which package the user wants delivered
            val selectedPackage: String = packageDropdown.selectedItem.toString()

            // Check whether GTID is valid
            val gtid = gtidEditText.text

            if (!(selectedStation.equals("not selected").or(selectedPackage.equals("")).or(gtid.length != 9))) {
                val order: Order = Order(selectedStation, selectedPackage, gtid.toString().toInt())

                // Create a database reference for Firebase and set its value to the Order triple.
//                val databaseReference: DatabaseReference = database.getReference(gtid.toString())
//                databaseReference.setValue(order)

                firestoredb.collection("orders")
                    .add(order)
                    .addOnSuccessListener {
                        Toast.makeText(
                            this.context,
                            "Success!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            this.context,
                            e.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
            } else {
                Toast.makeText(
                    this.context,
                    "Failure.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }


}