package com.example.taskmate.ui.viewmodel

import com.example.taskmate.data.entity.Task

sealed class TaskIntent {
    object LoadTasks : TaskIntent()
    data class AddTask(val task: Task) : TaskIntent()
    data class UpdateTask(val task: Task) : TaskIntent()
    data class DeleteTask(val taskId: Int) : TaskIntent()
    data class LoadTaskById(val taskId: Int) : TaskIntent() // New Intent
}
