package com.xeross.xerstats.controller.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xeross.xerstats.R
import kotlinx.android.synthetic.main.level_details_content.view.*

class LevelFourFragment  : FragmentManager() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.level_details_content, container, false)
        initializeLevel(view, R.drawable.level_4, "Golden Ridge", 4)
        return view    }

    companion object {
        fun newInstance() = LevelFourFragment()
    }
}