package com.example.mechanicdb.database.task

import androidx.annotation.WorkerThread
import androidx.lifecycle.ViewModelProvider
import com.example.mechanicdb.database.task.TaskDao
import com.example.mechanicdb.models.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskRepository(private val taskDao: TaskDao, private val scope: CoroutineScope) {

    val allTasks: Flow<List<Task>> = taskDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(task: Task) {
        scope.launch { taskDao.insertTask(task) }

    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun remove(task: Task) {
        scope.launch { taskDao.deleteTask(task) }

    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getTasks(vid: Int): Flow<List<Task>?> {
        return taskDao.getTasks(vid)
    }
}