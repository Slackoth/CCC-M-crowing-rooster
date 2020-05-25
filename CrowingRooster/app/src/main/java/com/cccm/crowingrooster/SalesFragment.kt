package com.cccm.crowingrooster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.cccm.crowingrooster.databinding.FragmentSalesBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class SalesFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var bind: FragmentSalesBinding
    private var listOfFragment: MutableList<Fragment> = mutableListOf(SuccessfulSalesFragment(),
        OngoingSalesFragment())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sales, container, false)

        bind = DataBindingUtil.inflate(inflater,R.layout.fragment_sales,container, false)

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.sales).capitalize()
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.seller_drawer_menu_navigation)
        }

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        val tabAdapter = GenericTabAdapter(listOfFragment,this)
        viewPager = bind.salesPager
        tabLayout = bind.salesTablayout

        viewPager.adapter = tabAdapter
        TabLayoutMediator(tabLayout,viewPager,TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when(position) {
                0-> tab.text = getString(R.string.successful_sales)
                1-> tab.text = getString(R.string.ongoing_sales)
                else -> tab.text = "Mr. Peanutbutter's House"
            }
        }).attach()

    }
}

