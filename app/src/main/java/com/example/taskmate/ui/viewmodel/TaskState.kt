package com.example.taskmate.ui.viewmodel

import com.example.taskmate.data.entity.Task

data class TaskState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)