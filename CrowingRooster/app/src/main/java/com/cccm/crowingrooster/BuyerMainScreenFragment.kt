package com.cccm.crowingrooster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import com.cccm.crowingrooster.databinding.FragmentBuyerMainScreenBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class BuyerMainScreenFragment : Fragment() {
    private lateinit var bind: FragmentBuyerMainScreenBinding
    lateinit var profileBt: CardView
    lateinit var ordersBt: CardView
    lateinit var vehiclesBt: CardView
    lateinit var batteryBt: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_buyer_main_screen, container, false)
        bind = DataBindingUtil.inflate(inflater,R.layout.fragment_buyer_main_screen,container,false)

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.main_menu)
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.buyer_drawer_menu_navigation)
        }

        bind.apply {
            profileBt = profileCvBtt
            ordersBt = orderCvBtt
            vehiclesBt = vehicleCvBtt
            batteryBt = batteryCvBtt
        }

        return bind.root
    }

}