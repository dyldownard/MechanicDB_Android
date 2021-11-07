package com.example.mechanicdb.database.task

import androidx.room.*
import com.example.mechanicdb.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM task WHERE vid = :vid")
    fun getAll(vid: Int): Flow<List<Task>>

}