package com.example.taskmate.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskmate.data.entity.Task
import kotlinx.coroutines.flow.Flow


//2 - create a DAO interface to define methods for interacting with the database.


@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task): Long

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :taskId LIMIT 1")
    suspend fun getTaskById(taskId: Int): Task?
}


//@Dao
//interface TaskDao {
//    @Query("SELECT * FROM tasks")
//    fun getAllTasks(): Flow<List<Task>>
//
//    @Query("SELECT * FROM tasks WHERE id = :taskId")
//    fun getTaskById(taskId: Int): Flow<Task?>
//
//    @Insert
//    suspend fun insert(task: Task)
//
//    @Update
//    suspend fun update(task: Task)
//
//    @Query("DELETE FROM tasks WHERE id = :taskId")
//    suspend fun delete(taskId: Int)
//}

//@Dao
//interface TaskDao {
//    @Query("SELECT * FROM tasks")
//    fun getAllTasks(): Flow<List<Task>>
//
//    @Insert
//    suspend fun insert(task: Task)
//
//    @Update
//    suspend fun update(task: Task)
//
//    @Query("DELETE FROM tasks WHERE id = :taskId")
//    suspend fun delete(taskId: Int)
//}
