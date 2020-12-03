package com.xeross.xerstats.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xeross.xerstats.models.LevelModel


@Dao
interface LevelDao {

    @Query("SELECT * FROM LevelModel WHERE idLevel = :id")
    fun getLevel(id: Int): LiveData<LevelModel>

    @Query("SELECT * FROM LevelModel")
    fun getLevels(): LiveData<List<LevelModel>>

    @Query("UPDATE LevelModel SET pbLevel=:pb WHERE idLevel = :id")
    fun updatePB(id: Int, pb: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLevel(item: LevelModel)
}