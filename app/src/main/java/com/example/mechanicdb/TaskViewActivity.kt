package com.example.mechanicdb

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mechanicdb.models.Task
import com.example.mechanicdb.models.Vehicle

class TaskViewActivity : AppCompatActivity() {

    lateinit var task : Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_view)

        println("hi")

        task = intent.getParcelableExtra<Task>("task")!!

        findViewById<TextView>(R.id.taskName).text = task.name
        findViewById<TextView>(R.id.completedBy).text = task.compby
        findViewById<TextView>(R.id.description).text = task.desc
        findViewById<TextView>(R.id.odometer).text = task.odo
        findViewById<TextView>(R.id.price).text = task.tprice
        findViewById<TextView>(R.id.dateCompleted).text = task.datecomp
        findViewById<TextView>(R.id.dateStarted).text = task.datestart
    }

}