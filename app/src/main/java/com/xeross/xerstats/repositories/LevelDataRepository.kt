package com.xeross.xerstats.repositories

import com.xeross.xerstats.database.dao.LevelDao
import com.xeross.xerstats.models.LevelModel


class LevelDataRepository(private var levelDao: LevelDao) {

    // GET
    fun getLevel(id: Int) = levelDao.getLevel(id)


    fun getLevels() = levelDao.getLevels()

    // CREATE
    fun insertLevel(levelModel: LevelModel) = levelDao.insertLevel(levelModel)


    fun updatePB(id: Int, pb: String) = levelDao.updatePB(id, pb)

}