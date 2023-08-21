package com.example.a2hw6month

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class TaskViewModel : ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>(emptyList())
    val tasks: LiveData<List<Task>> = _tasks

    fun addTask(task: Task) {
        val currentTasks = _tasks.value?.toMutableList() ?: mutableListOf()
        currentTasks.add(task)
        _tasks.value = currentTasks
    }

    fun updateTask(updatedTask: Task) {
        val currentTasks = _tasks.value?.toMutableList() ?: return
        val taskIndex = currentTasks.indexOfFirst { it.id == updatedTask.id }
        if (taskIndex != -1) {
            currentTasks[taskIndex] = updatedTask
            _tasks.value = currentTasks
        }
    }

    fun deleteTask(task: Task) {
        val currentTasks = _tasks.value?.toMutableList() ?: return
        currentTasks.remove(task)
        _tasks.value = currentTasks
    }
}