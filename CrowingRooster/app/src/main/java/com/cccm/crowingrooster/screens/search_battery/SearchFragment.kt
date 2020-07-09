package com.cccm.crowingrooster.screens.search_battery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentSearchBinding
import com.cccm.crowingrooster.generic_tab_adapter.GenericTabAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class SearchFragment : Fragment () {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var bind: FragmentSearchBinding
    private var listOfFragment: MutableList<Fragment> = mutableListOf(
        BatterySearchFragment(),
        CarSearchFragment()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sales, container, false)

        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_search,container, false)
        (activity as MainActivity).run {
            supportActionBar?.title = getString(R.string.search).capitalize()

            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.buyer_drawer_menu_navigation)
            showTopBar()


        }
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        val tabAdapter =
            GenericTabAdapter(
                listOfFragment,
                this,
                ""
            )
        viewPager = bind.searchPager
        tabLayout = bind.searchTablayout

        viewPager.adapter = tabAdapter
        TabLayoutMediator(tabLayout,viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when(position) {
                    0-> tab.text = getString(R.string.battery_search)
                    1-> tab.text = getString(R.string.car_search)
                    else -> tab.text = "Rooster is in problems"
                }
            }).attach()

    }



}