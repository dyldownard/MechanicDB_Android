package com.example.mechanicdb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechanicdb.models.DataSource
import kotlinx.android.synthetic.main.activity_garage_list.*
import kotlinx.android.synthetic.main.sign_in.*

class MainActivity : AppCompatActivity() {

    private lateinit var vehicleAdapter: GarageRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_garage_list)
        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet() {
        val data = DataSource.createDataSet()
        vehicleAdapter.submitList(data)
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