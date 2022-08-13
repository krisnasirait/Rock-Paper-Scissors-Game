package com.krisna.rockpaperscissors.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class LandingPageAdapter (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    private val fragments: ArrayList<Fragment> = ArrayList()


    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun setFragments(fragment: List<Fragment>) {
        this.fragments.clear()
        this.fragments.addAll(fragment)
    }


}