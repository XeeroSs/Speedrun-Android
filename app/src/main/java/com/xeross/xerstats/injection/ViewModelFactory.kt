package com.xeross.xerstats.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xeross.xerstats.controller.viewmodel.LevelViewModel
import com.xeross.xerstats.repositories.LevelDataRepository
import java.lang.IllegalArgumentException
import java.util.concurrent.Executor


class ViewModelFactory(private var levelDataRepository: LevelDataRepository,
                       private var executor: Executor) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LevelViewModel::class.java)) {
            return LevelViewModel(levelDataRepository, executor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}