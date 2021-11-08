package com.example.mechanicdb.database.task

import androidx.lifecycle.*
import com.example.mechanicdb.models.Task
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    val allTasks: MutableLiveData<List<Task>> = repository.allTasks.asLiveData() as MutableLiveData<List<Task>>

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun remove(task: Task) = viewModelScope.launch {
        repository.remove(task)
    }

    fun getTasks(vid: Int) = viewModelScope.launch {
        repository.getTasks(vid).collect { allTasks.postValue(it) }
    }
}

class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}