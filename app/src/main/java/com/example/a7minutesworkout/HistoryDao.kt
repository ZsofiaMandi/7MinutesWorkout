package com.example.a7minutesworkout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity) // This is a coroutine

    // Getting all of the done workouts
    @Query("SELECT * FROM `history-table`")
    fun fetchAllWorkouts(): Flow<List<HistoryEntity>>

}