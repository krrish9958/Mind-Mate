package com.example.mindmate.dao

import androidx.room.*
import com.example.mindmate.entities.Journals

@Dao
interface JournalsDao {

    @Query("SELECT * FROM journals ORDER BY id DESC")
    suspend fun getAllJournals() : List<Journals>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJournals(journal : Journals)

    @Delete
    suspend fun deleteJournals(journal: Journals)
}