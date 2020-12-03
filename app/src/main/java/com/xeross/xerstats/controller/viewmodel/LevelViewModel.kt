package com.xeross.xerstats.controller.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xeross.xerstats.models.LevelModel
import com.xeross.xerstats.repositories.LevelDataRepository
import java.util.concurrent.Executor

class LevelViewModel(
    private var levelDataRepository: LevelDataRepository,
    private var executor: Executor
) : ViewModel() {

    // GETS PROPERTY
    fun getLevel(id: Int) = levelDataRepository.getLevel(id)


    fun getLevels() = levelDataRepository.getLevels()

    // CREATE PROPERTY
    fun insertLevel(levelModel: LevelModel) = executor.execute {
        levelDataRepository.insertLevel(levelModel)
    }

    fun updatePB(id: Int, pb: String) = executor.execute {
        levelDataRepository.updatePB(id, pb)
    }

}