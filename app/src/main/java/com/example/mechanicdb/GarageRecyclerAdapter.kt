package com.example.mechanicdb

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanicdb.models.Vehicle

import kotlinx.android.synthetic.main.garage_list_item.view.*

class GarageRecyclerAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Vehicle> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VehicleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.garage_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is VehicleViewHolder -> {
                val vehicle = items[position]
                holder.itemView.setOnClickListener{
                    onClickListener.onClick(vehicle)
                }
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(vehicleList: List<Vehicle>) {
        items = vehicleList
    }

    class VehicleViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        val vehicle_image: ImageView = itemView.vehicleImageView
        val vehicle_make: TextView = itemView.makeText
        val vehicle_model: TextView = itemView.modelText
        val vehicle_year: TextView = itemView.yearText
        val vehicle_milage: TextView = itemView.mileageText

        fun bind(vehicle: Vehicle) {
            vehicle_make.setText("Make: ".plus(vehicle.make))
            vehicle_model.setText("Model: ".plus(vehicle.model))
            vehicle_year.setText("Year: ".plus(vehicle.year.toString()))
            vehicle_milage.setText("Miles: ".plus(vehicle.milage.toString()))
            vehicle_image.apply {
                setImageResource(R.drawable.ic_baseline_directions_car_24)
                contentDescription = "a car"
            }
        }
    }

    class OnClickListener(val clickListener: (vehicle: Vehicle) -> Unit) {
        fun onClick(vehicle: Vehicle) = clickListener(vehicle)
    }
}