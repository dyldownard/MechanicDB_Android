package com.example.mechanicdb

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mechanicdb.models.Vehicle
import kotlinx.android.synthetic.main.activity_main.*

class VehicleViewActivity : AppCompatActivity() {

    lateinit var vehicle : Vehicle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.maintainence_list)

        vehicle = intent.getParcelableExtra<Vehicle>("vehicle")!!
        val make = findViewById<TextView>(R.id.vMakeText)
        val model = findViewById<TextView>(R.id.vModelText)
        val year = findViewById<TextView>(R.id.vYearText)
        val mileage = findViewById<TextView>(R.id.vMileageText)
        val vin = findViewById<TextView>(R.id.vVinText)

        make.setText("Make: ".plus(vehicle.make))
        model.setText("Model: ".plus(vehicle.model))
        year.setText("Year: ".plus(vehicle.year.toString()))
        mileage.setText("Miles: ".plus(vehicle.milage.toString()))
        vin.setText("VIN: ".plus(vehicle.VIN.toString()))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.vehicle_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        println(item.itemId)
        if (item.itemId === R.id.removeVehicleMenu) {
            val dialogBuilder = AlertDialog.Builder(this)
            // set message of alert dialog
            dialogBuilder
                .setCancelable(false)
                .setPositiveButton("Proceed") { dialog, id ->
                    dialog.cancel()
                    val replyIntent = Intent()
                    replyIntent.putExtra("vehicle", vehicle)
                    setResult(Activity.RESULT_OK, replyIntent)
                    finish()
                }
                // negative button text and action
                .setNegativeButton("Cancel") { dialog, id ->
                    dialog.cancel()
                }
            val alert = dialogBuilder.create()
            alert.setTitle("Delete Vehicle")
            alert.show()
        }else {
            finish()
        }
        return false
    }

}