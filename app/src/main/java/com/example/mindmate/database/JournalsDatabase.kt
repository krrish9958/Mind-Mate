package com.example.mindmate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mindmate.dao.JournalsDao
import com.example.mindmate.entities.Journals


@Database(entities = [Journals::class], version = 1, exportSchema = false)
abstract class JournalsDatabase : RoomDatabase() {


    companion object{
        var journalsDatabase : JournalsDatabase?= null

        @Synchronized
        fun getDatabase(context: Context) : JournalsDatabase{
            if(journalsDatabase==null){
                journalsDatabase = Room.databaseBuilder(
                    context
                    , JournalsDatabase::class.java
                    , "journals.db"
                ).build()
            }
            return journalsDatabase!!
        }
    }

    abstract fun journalsDao() : JournalsDao
}