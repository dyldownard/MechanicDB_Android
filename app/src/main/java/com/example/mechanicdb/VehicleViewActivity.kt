package com.example.mechanicdb

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mechanicdb.models.Vehicle

class VehicleViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.maintainence_list)

        val vehicle: Vehicle? = intent.getParcelableExtra<Vehicle>("vehicle")
        val make = findViewById<TextView>(R.id.vMakeText)
        val model = findViewById<TextView>(R.id.vModelText)
        val year = findViewById<TextView>(R.id.vYearText)
        val mileage = findViewById<TextView>(R.id.vMileageText)
        val vin = findViewById<TextView>(R.id.vVinText)

        if (vehicle != null) {
            make.setText("Make: ".plus(vehicle.make))
            model.setText("Model: ".plus(vehicle.model))
            year.setText("Year: ".plus(vehicle.year.toString()))
            mileage.setText("Miles: ".plus(vehicle.milage.toString()))
            vin.setText("VIN: ".plus(vehicle.VIN.toString()))
        }
    }

}