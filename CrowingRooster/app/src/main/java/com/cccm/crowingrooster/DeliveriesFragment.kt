package com.cccm.crowingrooster

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.cccm.crowingrooster.databinding.FragmentDeliveriesBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class DeliveriesFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var bind: FragmentDeliveriesBinding
    private var listOfFragment: MutableList<Fragment> = mutableListOf(OpenOrdersFragment(), ConcludedOrdersFragment())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_deliveries, container, false)

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = "Entregas".capitalize()
        }

        setHasOptionsMenu(true)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabAdapter = GenericTabAdapter(listOfFragment, this)
        viewPager = bind.deliveriesPager
        tabLayout = bind.deliveriesTablayout

        viewPager.adapter = tabAdapter
        TabLayoutMediator(tabLayout, viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when(position) {
                0-> tab.text = "Entregas en curso"
                1-> tab.text = "Entregas éxitosas"
                else -> tab.text = "Defying Gravity"
            }
        }).attach()
    }
}