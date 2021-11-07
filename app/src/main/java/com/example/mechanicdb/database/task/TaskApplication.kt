package com.example.mechanicdb.database.task

import android.app.Application
import com.example.mechanicdb.database.task.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TaskApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        TaskRoomDatabase.getDatabase(this, applicationScope)
    }

    val repository by lazy {
        TaskRepository(database.taskDao(), applicationScope)
    }
}