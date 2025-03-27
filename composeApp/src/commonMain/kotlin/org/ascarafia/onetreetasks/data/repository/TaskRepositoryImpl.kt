package org.ascarafia.onetreetasks.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.ascarafia.onetreetasks.data.database.TaskDAO
import org.ascarafia.onetreetasks.domain.model.Task
import org.ascarafia.onetreetasks.domain.repository.TaskRepository

class TaskRepositoryImpl(
    private val taskDAO: TaskDAO
): TaskRepository {
    override fun getTasks(): Flow<List<Task>> {
        return taskDAO.getTaskList().map { taskEntityList ->
            taskEntityList.map { it.toTask() }
        }
    }

    override suspend fun addTask(newTask: Task) {
        taskDAO.upsert(newTask.toTaskEntity())
    }

    override suspend fun deleteTask(task: Task) {
        taskDAO.deleteTask(task.id)
    }

    override suspend fun getTaskById(taskId: String): Task? {
        return taskDAO.getTask(taskId)?.toTask()
    }
}