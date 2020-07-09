package com.cccm.crowingrooster.generic_tab_adapter

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class GenericTabAdapter(
    listOfFragment: MutableList<Fragment>,
    fragment: Fragment,
    private val sellerCode: String?
) : FragmentStateAdapter(fragment) {

    private var listFragment: MutableList<Fragment> = listOfFragment

    //Gets the number of tabs to be created
    override fun getItemCount(): Int = listFragment.size

    //Creates the fragment depending on the position of the tab
    override fun createFragment(position: Int): Fragment {
        var cont = 0
        val fragArgs = Bundle()
        fragArgs.putString("sellerCode",sellerCode)
        for (fragment in listFragment) {
            fragment.arguments = fragArgs
            if (position == cont) break else cont++
        }

        return listFragment[cont]
    }

}