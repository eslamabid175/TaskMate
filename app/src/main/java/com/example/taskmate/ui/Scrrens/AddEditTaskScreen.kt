// AddEditTaskScreen.kt
package com.example.taskmate.ui.Scrrens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmate.data.entity.Task
import com.example.taskmate.ui.viewmodel.TaskIntent
import com.example.taskmate.ui.viewmodel.TaskViewModel

@Composable
fun AddEditTaskScreen(
    task: Task? = null,
    onSave: () -> Unit = {},
    viewModel: TaskViewModel = viewModel()
) {
    // Local state for form fields
    var title by remember { mutableStateOf(task?.title ?: "") }
    var description by remember { mutableStateOf(task?.description ?: "") }
    var isCompleted by remember { mutableStateOf(task?.isCompleted ?: false) }
    val isEditing = task != null

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEditing) "Edit Task" else "Add Task") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text("Completed")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = isCompleted,
                    onCheckedChange = { isCompleted = it }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val taskToSave = Task(
                        id = task?.id ?: 0,
                        title = title,
                        description = description,
                        isCompleted = isCompleted
                    )
                    if (isEditing) {
                        viewModel.processIntent(TaskIntent.UpdateTask(taskToSave))
                    } else {
                        viewModel.processIntent(TaskIntent.AddTask(taskToSave))
                    }
                    onSave()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isEditing) "Update Task" else "Add Task")
            }
        }
    }
}
