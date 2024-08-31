// TaskViewModel.kt
package com.example.taskmate.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmate.data.entity.Task
import com.example.taskmate.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    private val _state = MutableStateFlow(TaskState(isLoading = true))
    val state: StateFlow<TaskState> get() = _state

    private val _taskState = MutableStateFlow<Task?>(null)
    val taskState: StateFlow<Task?> get() = _taskState

    init {
        processIntent(TaskIntent.LoadTasks)
    }

    fun processIntent(intent: TaskIntent) {
        when (intent) {
            is TaskIntent.LoadTasks -> loadTasks()
            is TaskIntent.AddTask -> addTask(intent.task)
            is TaskIntent.UpdateTask -> updateTask(intent.task)
            is TaskIntent.DeleteTask -> deleteTask(intent.taskId)
            is TaskIntent.LoadTaskById -> loadTaskById(intent.taskId)
        }
    }

    private fun loadTasks() {
        viewModelScope.launch {
            taskRepository.getAllTasks()
                .onStart { _state.value = _state.value.copy(isLoading = true) }
                .catch {  _state.value = _state.value.copy(error = "An error occurred") }
                .collect { tasks ->
                    _state.value = _state.value.copy(tasks = tasks, isLoading = false)
                }
        }
    }

    private fun addTask(task: Task) {
        viewModelScope.launch {
            taskRepository.insert(task)
            loadTasks() // Refresh the task list
        }
    }

    private fun updateTask(task: Task) {
        viewModelScope.launch {
            taskRepository.update(task)
            loadTasks() // Refresh the task list
        }
    }

    private fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            taskRepository.delete(taskId)
            loadTasks() // Refresh the task list
        }
    }

    private fun loadTaskById(taskId: Int) {
        viewModelScope.launch {
            taskRepository.getTaskById(taskId)
                .onStart { _taskState.value = null }
                .catch { e -> _taskState.value = null }
                .collect { task ->
                    _taskState.value = task
                }
        }
    }
}
