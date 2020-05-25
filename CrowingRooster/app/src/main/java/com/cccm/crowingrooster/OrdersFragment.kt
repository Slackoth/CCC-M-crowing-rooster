package com.cccm.crowingrooster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.cccm.crowingrooster.databinding.FragmentOrdersBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OrdersFragment : Fragment () {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var bind: FragmentOrdersBinding
    private var listOfFragment: MutableList<Fragment> = mutableListOf(SuccessfulOrdersFragment(),
        OngoingOrdersFragment(), CanceledOrdersFragment())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sales, container, false)

        bind = DataBindingUtil.inflate(inflater,R.layout.fragment_orders,container, false)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.orders).capitalize()

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        val tabAdapter = GenericTabAdapter(listOfFragment,this)
        viewPager = bind.ordersPager
        tabLayout = bind.ordersTablayout

        viewPager.adapter = tabAdapter
        TabLayoutMediator(tabLayout,viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when(position) {
                    0-> tab.text = getString(R.string.successful_orders)
                    1-> tab.text = getString(R.string.ongoing_orders)
                    2 -> tab.text= getString(R.string.canceled_orders)
                    else -> tab.text = "UN GALLO CON TENIS JAJAJAJAJA"
                }
            }).attach()

    }



}