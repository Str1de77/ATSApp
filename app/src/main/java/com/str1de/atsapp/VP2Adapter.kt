package com.str1de.atsapp

import Fragments.RandomTripFragment
import Fragments.TimeFragment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class VP2Adapter(fa: FragmentActivity, private val list: List<Fragment>) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }

}