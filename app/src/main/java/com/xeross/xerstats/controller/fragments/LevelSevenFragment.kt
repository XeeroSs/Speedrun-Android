package com.xeross.xerstats.controller.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xeross.xerstats.R

class LevelSevenFragment : FragmentManager() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.level_details_content, container, false)
        initializeLevel(view, R.drawable.level_7, "The Summit", 7)
        return view
    }

    companion object {
        fun newInstance() = LevelSevenFragment()
    }
}