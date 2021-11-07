package com.example.mechanicdb

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechanicdb.database.vehicle.VehicleApplication
import com.example.mechanicdb.database.vehicle.VehicleViewModel
import com.example.mechanicdb.database.vehicle.VehicleViewModelFactory
import com.example.mechanicdb.models.Vehicle
import kotlinx.android.synthetic.main.activity_garage_list.*
import kotlinx.android.synthetic.main.sign_in.*


class MainActivity : AppCompatActivity() {

    private lateinit var vehicleAdapter: GarageRecyclerAdapter

    private val newVehicleRequestCode = 1
    private val vehicleViewModel: VehicleViewModel by viewModels {
        VehicleViewModelFactory((application as VehicleApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_garage_list)
        initRecyclerView()

        vehicleViewModel.allVehicles.observe(this, Observer { vehicles ->
            vehicles.let {
                vehicleAdapter.submitList(it)
            }
        })


        addVehicleButton.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(this)

            val makeInput = EditText(this)
            val modelInput = EditText(this)
            val yearInput = EditText(this)
            val mileageInput = EditText(this)
            val vinInput = EditText(this)

            val llayout = LinearLayout(this)
            llayout.orientation = LinearLayout.VERTICAL

            makeInput.hint = "Make"
            modelInput.hint = "Model"
            yearInput.hint = "Year"
            mileageInput.hint = "Mileage"
            vinInput.hint = "VIN"

            makeInput.inputType = InputType.TYPE_CLASS_TEXT
            modelInput.inputType = InputType.TYPE_CLASS_TEXT
            yearInput.inputType = InputType.TYPE_CLASS_NUMBER
            mileageInput.inputType = InputType.TYPE_CLASS_NUMBER
            vinInput.inputType = InputType.TYPE_CLASS_NUMBER

            llayout.addView(makeInput)
            llayout.addView(modelInput)
            llayout.addView(yearInput)
            llayout.addView(mileageInput)
            llayout.addView(vinInput)

            // set message of alert dialog
            dialogBuilder
                .setCancelable(false)
                .setView(llayout)
                .setPositiveButton("Proceed") { dialog, id ->
                    var veh = Vehicle(makeInput.text.toString(), modelInput.text.toString(), yearInput.text.toString(), mileageInput.text.toString(), 1,0, vinInput.text.toString())
                    vehicleViewModel.insert(veh)
                    dialog.cancel()
                }
                // negative button text and action
                .setNegativeButton("Cancel") { dialog, id ->
                    dialog.cancel()
                }

            val alert = dialogBuilder.create()
            alert.setTitle("Add Vehicle")
            alert.show()
            //vehicleViewModel.insert(Vehicle("Ford", "Mustang", "1965", "1000", 1, 0, "10002"))
        }
    }

    private fun initRecyclerView() {
        var app = this
        vehicle_recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacing = RecyclerItemSpacing(30)
            addItemDecoration(topSpacing)
            vehicleAdapter = GarageRecyclerAdapter(GarageRecyclerAdapter.OnClickListener { vehicle ->
                val intent = Intent(app, VehicleViewActivity::class.java).apply {
                    putExtra("vehicle", vehicle)
                }
                startActivityForResult(intent, 1)
            })
            adapter = vehicleAdapter
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (resultCode == Activity.RESULT_OK) {
            intentData!!.getParcelableExtra<Vehicle>("vehicle")?.let{ reply ->
                vehicleViewModel.remove(reply)
            }
        }
    }
}

