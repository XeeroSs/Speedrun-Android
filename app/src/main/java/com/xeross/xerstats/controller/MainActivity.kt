package com.xeross.xerstats.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.xeross.xerstats.LevelAdapter
import com.xeross.xerstats.R
import com.xeross.xerstats.controller.fragments.*
import kotlinx.android.synthetic.main.activity_level.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager_levels)

        val fragmentList = arrayListOf(
            LevelOneFragment.newInstance(),
            LevelTwoFragment.newInstance(),
            LevelThreeFragment.newInstance(),
            LevelFourFragment.newInstance(),
            LevelFiveFragment.newInstance(),
            LevelSixFragment.newInstance(),
            LevelSevenFragment.newInstance(),
            LevelResultFragment.newInstance()
        )
        viewPager2.adapter = LevelAdapter(this, fragmentList)
    }
}
