package com.cccm.crowingrooster.screens.sales

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.cccm.crowingrooster.*
import com.cccm.crowingrooster.databinding.FragmentSalesBinding
import com.cccm.crowingrooster.generic_tab_adapter.GenericTabAdapter
import com.cccm.crowingrooster.screens.ascending_descending_search.AscDescDialogFragment
import com.cccm.crowingrooster.screens.sales.ongoing_sales.OngoingSalesFragment
import com.cccm.crowingrooster.screens.sales.successful_sales.SuccessfulSalesFragment
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
    private var listOfFragment: MutableList<Fragment> = mutableListOf(
        SuccessfulSalesFragment(),
        OngoingSalesFragment()
    )
    private var args: SalesFragmentArgs? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sales, container, false)

        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_sales,container, false)

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.sales).capitalize()
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.seller_drawer_menu_navigation)
        }

        args = arguments?.let {
            SalesFragmentArgs.fromBundle(it)
        }

        setHasOptionsMenu(true)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        val tabAdapter =
            GenericTabAdapter(
                listOfFragment,
                this,
                args?.sellerCode
            )
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.successful_sales_more_options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return when(item.itemId) {
            R.id.action_order_date, R.id.action_order_client -> {
                val dialog =
                    AscDescDialogFragment()
                dialog.show(requireActivity().supportFragmentManager,"AscDescDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

