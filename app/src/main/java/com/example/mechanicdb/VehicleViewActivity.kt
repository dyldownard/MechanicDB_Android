package com.example.mechanicdb

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechanicdb.database.MechanicApplication
import com.example.mechanicdb.database.task.TaskViewModel
import com.example.mechanicdb.database.task.TaskViewModelFactory
import com.example.mechanicdb.models.Task
import com.example.mechanicdb.models.Vehicle
import kotlinx.android.synthetic.main.activity_garage_list.*
import kotlinx.android.synthetic.main.maintainence_list.*

class VehicleViewActivity() : AppCompatActivity() {

    private lateinit var taskAdapter: TaskRecyclerAdapter

    lateinit var vehicle : Vehicle

    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as MechanicApplication).task_repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.maintainence_list)

        vehicle = intent.getParcelableExtra<Vehicle>("vehicle")!!
        val make = findViewById<TextView>(R.id.vMakeText)
        val model = findViewById<TextView>(R.id.vModelText)
        val year = findViewById<TextView>(R.id.vYearText)
        val mileage = findViewById<TextView>(R.id.vMileageText)
        val vin = findViewById<TextView>(R.id.vVinText)

        make.text = "Make: ".plus(vehicle.make)
        model.text = "Model: ".plus(vehicle.model)
        year.text = "Year: ".plus(vehicle.year.toString())
        mileage.text = "Miles: ".plus(vehicle.milage.toString())
        vin.text = "VIN: ".plus(vehicle.VIN.toString())


        initRecyclerView()

        taskViewModel.getTasks(vehicle.vid)
        taskViewModel.allTasks.observe(this, Observer { tasks ->
            tasks.let {
                taskAdapter.submitList(it)
            }
        })

        addTaskButton.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(this)

            val taskInput = EditText(this)
            val compByInput = EditText(this)
            val descInput = EditText(this)
            val priceInput = EditText(this)
            val odoInput = EditText(this)
            val dateCompInput = EditText(this)
            val dateStartInput = EditText(this)


            val llayout = LinearLayout(this)
            llayout.orientation = LinearLayout.VERTICAL

            taskInput.hint = "Task Name"
            compByInput.hint = "Completed By"
            descInput.hint = "Description of Task"
            priceInput.hint = "Total Price"
            odoInput.hint = "Odometer"
            dateCompInput.hint = "Date Completed"
            dateStartInput.hint = "Date Started"


            taskInput.inputType = InputType.TYPE_CLASS_TEXT
            compByInput.inputType = InputType.TYPE_CLASS_TEXT
            descInput.inputType = InputType.TYPE_CLASS_TEXT
            priceInput.inputType = InputType.TYPE_CLASS_NUMBER
            odoInput.inputType = InputType.TYPE_CLASS_NUMBER
            dateCompInput.inputType = InputType.TYPE_CLASS_DATETIME
            dateStartInput.inputType = InputType.TYPE_CLASS_DATETIME


            llayout.addView(taskInput)
            llayout.addView(compByInput)
            llayout.addView(descInput)
            llayout.addView(priceInput)
            llayout.addView(odoInput)
            llayout.addView(dateCompInput)
            llayout.addView(dateStartInput)

            // set message of alert dialog
            dialogBuilder
                .setCancelable(false)
                .setView(llayout)
                .setPositiveButton("Proceed") { dialog, id ->
                    var task = Task(0, vehicle.vid, taskInput.text.toString(), compByInput.text.toString(), descInput.text.toString(), odoInput.text.toString(), priceInput.text.toString(), dateCompInput.text.toString(), dateStartInput.text.toString())
                    taskViewModel.insert(task)
                    dialog.cancel()
                }
                // negative button text and action
                .setNegativeButton("Cancel") { dialog, id ->
                    dialog.cancel()
                }

            val alert = dialogBuilder.create()
            alert.setTitle("Add Task")
            alert.show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.vehicle_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
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

    private fun initRecyclerView() {
        var app = this
        task_recycler.apply {
            layoutManager = LinearLayoutManager(this@VehicleViewActivity)
            val topSpacing = RecyclerItemSpacing(30)
            addItemDecoration(topSpacing)
            taskAdapter = TaskRecyclerAdapter(TaskRecyclerAdapter.OnClickListener { task ->
                val intent = Intent(app, TaskViewActivity::class.java).apply {
                    putExtra("task", task)
                }
                startActivity(intent)
            })
            adapter = taskAdapter
        }
    }
}