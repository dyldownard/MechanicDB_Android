package com.example.mechanicdb

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.observe

import androidx.recyclerview.widget.LinearLayoutManager
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
            vehicles.let{
                vehicleAdapter.submitList(it)
            }
        })

        addVehicleButton.setOnClickListener{
            vehicleViewModel.insert(Vehicle("Ford","Mustang", "1965","1000",1, 0,"10002"))
        }
    }

    private fun initRecyclerView() {
        var app = this
        vehicle_recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacing = RecyclerItemSpacing(30)
            addItemDecoration(topSpacing)
            vehicleAdapter = GarageRecyclerAdapter(GarageRecyclerAdapter.OnClickListener {vehicle ->
                val intent = Intent(app, VehicleViewActivity::class.java).apply{
                    putExtra("vehicle", vehicle)
                }
                startActivityForResult(intent,1)
            })
            adapter = vehicleAdapter
        }
    }

}