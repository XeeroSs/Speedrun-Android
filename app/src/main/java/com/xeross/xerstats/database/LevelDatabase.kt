package com.xeross.xerstats.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.xeross.xerstats.models.LevelModel
import com.xeross.xerstats.database.dao.LevelDao


@Database(entities = [LevelModel::class], version = 1, exportSchema = false)
abstract class LevelDatabase : RoomDatabase() {

    abstract fun propertyDao(): LevelDao

    companion object {

        // Singleton
        @Volatile
        private var INSTANCE: LevelDatabase? = null

        // Instance
        fun getInstance(context: Context): LevelDatabase? {
            INSTANCE?.let { return it } ?: synchronized(LevelDatabase::class.java) {
                INSTANCE?.let { return it }
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        LevelDatabase::class.java, "Level.db")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE
        }
    }
}
