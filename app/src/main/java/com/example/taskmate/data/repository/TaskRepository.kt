// TaskRepository.kt
package com.example.taskmate.data.repository

import com.example.taskmate.data.dao.TaskDao
import com.example.taskmate.data.entity.Task
import kotlinx.coroutines.flow.Flow
//4 - The repository acts as an intermediary between the ViewModel and the data sources (Room, Retrofit).
class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) = taskDao.insert(task)

    suspend fun update(task: Task) = taskDao.update(task)

    suspend fun delete(task: Task) = taskDao.delete(task)

    suspend fun getTaskById(taskId: Int): Task? = taskDao.getTaskById(taskId)
}