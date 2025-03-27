package org.ascarafia.onetreetasks

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.ascarafia.onetreetasks.ui.task_list.TaskDetailScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.ascarafia.onetreetasks.ui.task_list.TaskListScreen
import org.ascarafia.onetreetasks.ui.task_list.TaskListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    val taskViewModel: TaskListViewModel = koinViewModel<TaskListViewModel>()

    MaterialTheme {
        NavHost(navController = navController, startDestination = "taskList") {
            composable("taskList") {
                TaskListScreen(navController, taskViewModel)
                taskViewModel.getDatabaseTasks()
            }

            composable("taskDetail/{taskId}") { backStackEntry ->
                val taskId = backStackEntry.arguments?.getString("taskId")
                TaskDetailScreen(navController, taskId, taskViewModel)
            }
        }
    }
}