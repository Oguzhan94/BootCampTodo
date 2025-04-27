package com.example.bootcamptodo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert
    suspend fun insertToDo(toDo: ToDoEntity)

    @Update
    suspend fun updateToDo(toDo: ToDoEntity)

    @Delete
    suspend fun deleteToDo(toDo: ToDoEntity)

    @Query("SELECT * FROM todos")
    fun getAllToDos(): Flow<List<ToDoEntity>>

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getToDoById(id: Int): Flow<ToDoEntity>
}