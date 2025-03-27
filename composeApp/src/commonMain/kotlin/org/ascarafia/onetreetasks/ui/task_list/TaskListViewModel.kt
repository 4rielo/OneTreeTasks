package org.ascarafia.onetreetasks.ui.task_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*
import org.ascarafia.onetreetasks.domain.model.Task

class TaskListViewModel: ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    fun getDatabaseTasks() {
        //TODO: get database tasks
    }

    fun addTask(task: Task) {
        //TODO: save task to database
    }

    private fun updateTask(task: Task) {
        //TODO: update Task
    }

    fun deleteTask(task: Task) {
        //TODO: deleteTask
    }

    fun toggleTaskCompletion(taskId: String) {
        val index = _tasks.value.indexOfFirst { it.id == taskId }
        if (index != -1) {
            _tasks.value = _tasks.value.toMutableList().apply {
                this[index] = this[index].copy(isCompleted = !this[index].isCompleted)
                updateTask(this[index])
            }
        }
    }

    fun getTaskById(taskId: String): Task? {
        return _tasks.value.find { it.id == taskId }
    }
}