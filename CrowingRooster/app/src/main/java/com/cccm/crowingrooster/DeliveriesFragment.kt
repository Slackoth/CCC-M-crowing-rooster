package com.cccm.crowingrooster

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.cccm.crowingrooster.databinding.FragmentDeliveriesBinding
import com.cccm.crowingrooster.generic_tab_adapter.GenericTabAdapter
import com.cccm.crowingrooster.screens.ascending_descending_search.AscDescDialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

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
            drawerLocked(true)
        }

        setHasOptionsMenu(true)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabAdapter =
            GenericTabAdapter(
                listOfFragment,
                this
            )
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

    override fun onDestroyView() {
        (activity as MainActivity).drawerLocked(false)
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.open_orders_more_options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
                R.id.action_log_off -> {
                    findNavController().navigate(DeliveriesFragmentDirections.actionDeliveriesFragmentToLogInFragment())
                    return true
                }
                R.id.action_order_by_date -> {
                    val dialog = AscDescDialogFragment()
                    dialog.show(requireActivity().supportFragmentManager, "AscDescDialog")
                    true
                }

            else -> super.onOptionsItemSelected(item)
        }
    }
}