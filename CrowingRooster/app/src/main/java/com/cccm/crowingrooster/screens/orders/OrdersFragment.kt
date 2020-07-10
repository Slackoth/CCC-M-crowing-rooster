package com.cccm.crowingrooster.screens.orders

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.cccm.crowingrooster.*
import com.cccm.crowingrooster.databinding.FragmentOrdersBinding
import com.cccm.crowingrooster.generic_tab_adapter.GenericTabAdapter
import com.cccm.crowingrooster.screens.orders.ongoing_orders.OngoingOrdersFragment
import com.cccm.crowingrooster.screens.orders.canceled_orders.CanceledOrdersFragment
import com.cccm.crowingrooster.screens.orders.successful_orders.SuccessfulOrdersFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class OrdersFragment : Fragment () {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var bind: FragmentOrdersBinding
    private var listOfFragment: MutableList<Fragment> = mutableListOf(
        SuccessfulOrdersFragment(),
        OngoingOrdersFragment()/*
        CanceledOrdersFragment()*/
    )
    private var args: OrdersFragmentArgs? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = inflate(inflater,
            R.layout.fragment_orders,container, false)
        (activity as MainActivity).run {supportActionBar?.title = getString(
            R.string.orders
        ).capitalize()
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.buyer_drawer_menu_navigation)
            showTopBar()
        }

        args = arguments?.let {
            OrdersFragmentArgs.fromBundle(it)
        }

        Log.d("orderFrag","${args?.buyerCode}")

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        val tabAdapter =
            GenericTabAdapter(
                listOfFragment,
                this,
                args?.buyerCode
            )
        viewPager = bind.ordersPager
        tabLayout = bind.ordersTablayout

        viewPager.adapter = tabAdapter
        TabLayoutMediator(tabLayout,viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when(position) {
                    0-> tab.text = getString(R.string.successful_orders)
                    1-> tab.text = getString(R.string.ongoing_orders)
                    /*2 -> tab.text= getString(R.string.canceled_orders)*/
                    else -> tab.text = "UN GALLO CON TENIS JAJAJAJAJA"
                }
            }).attach()

    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ongoing_orders_more_options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return when(item.itemId) {
            R.id.action_order_date, R.id.action_order_idorder -> {
                val dialog = AscDescDialogFragment()
                dialog.show(requireActivity().supportFragmentManager,"AscDescDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/


}