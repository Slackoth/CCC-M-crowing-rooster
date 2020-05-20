package com.cccm.crowingrooster

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class GenericTabAdapter(
    listOfFragment: MutableList<Fragment>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private var listFragment: MutableList<Fragment> = listOfFragment

    //Gets the number of tabs to be created
    override fun getItemCount(): Int = listFragment.size

    //Creates the fragment depending on the position of the tab
    override fun createFragment(position: Int): Fragment {
        var cont = 0
        for (fragment in listFragment) {
            if (position == cont) break else cont++
        }

        return listFragment[cont]
    }

}