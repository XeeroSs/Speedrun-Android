package com.xeross.xerstats.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.xeross.xerstats.controller.viewmodel.LevelViewModel
import com.xeross.xerstats.database.LevelDatabase
import com.xeross.xerstats.injection.ViewModelFactory
import com.xeross.xerstats.repositories.LevelDataRepository
import java.util.concurrent.Executors

object Utils {

    // Provide instance database
    private fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSourceProperty =
            LevelDataRepository(LevelDatabase.getInstance(context)!!.propertyDao())
        return ViewModelFactory(dataSourceProperty, Executors.newSingleThreadExecutor())
    }

    // ViewModel for Activity
    fun configureViewModel(context: FragmentActivity): LevelViewModel? {
        val viewModelProvider = provideViewModelFactory(context)
        return ViewModelProviders.of(context, viewModelProvider).get(LevelViewModel::class.java)
    }

    // ViewModel for Fragment
    fun configureViewModel(fragment: Fragment, context: Context): LevelViewModel? {
        val viewModelProvider = provideViewModelFactory(context)
        return ViewModelProviders.of(fragment, viewModelProvider).get(LevelViewModel::class.java)
    }

    // Show Toast
    fun showToast(context: Context, textId: Int) =
        Toast.makeText(context, context.getString(textId), Toast.LENGTH_SHORT).show()
}
