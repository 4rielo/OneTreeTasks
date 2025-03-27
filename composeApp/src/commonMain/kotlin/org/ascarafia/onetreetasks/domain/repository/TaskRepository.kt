package org.ascarafia.onetreetasks.domain.repository

import kotlinx.coroutines.flow.Flow
import org.ascarafia.onetreetasks.domain.model.Task

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>

    suspend fun addTask(newTask: Task)

    suspend fun deleteTask(task: Task)

    suspend fun getTaskById(taskId: String): Task?
}