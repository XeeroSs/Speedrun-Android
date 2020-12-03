package com.xeross.xerstats

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xeross.xerstats.controller.fragments.FragmentManager

class LevelAdapter(fragmentActivity: FragmentActivity, private val fragments: ArrayList<FragmentManager>) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}