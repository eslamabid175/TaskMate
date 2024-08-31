package com.example.taskmate.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmate.ui.Scrrens.AddEditTaskScreen
import com.example.taskmate.ui.Scrrens.TaskList
import com.example.taskmate.ui.viewmodel.TaskIntent
import com.example.taskmate.ui.viewmodel.TaskViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    val taskViewModel: TaskViewModel = getViewModel()

    NavHost(navController, startDestination = "taskList") {
        composable("taskList") {
            TaskList(
                viewModel = taskViewModel,
                onAddTask = {
                    navController.navigate("addEditTask")
                },
                onEditTask = { task ->
                    navController.navigate("addEditTask/${task.id}")
                }
            )
        }
        composable("addEditTask/{taskId?}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            if (taskId != null) {
                taskViewModel.processIntent(TaskIntent.LoadTaskById(taskId))
            }

            val task by taskViewModel.taskState.collectAsState()

            AddEditTaskScreen(
                task = task,
                onSave = {
                    navController.popBackStack() // Return to the previous screen
                },
                viewModel = taskViewModel
            )
        }
    }
}
