package com.cccm.crowingrooster.screens.buyer_main_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentBuyerMainScreenBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class BuyerMainScreenFragment : Fragment() {
    private lateinit var bind: FragmentBuyerMainScreenBinding
    lateinit var profileBt: CardView
    lateinit var ordersBt: CardView
    lateinit var chatBt: CardView
    lateinit var batteryBt: CardView
    private var args: BuyerMainScreenFragmentArgs? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_buyer_main_screen, container, false)
        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_buyer_main_screen,container,false)

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.main_menu)
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.buyer_drawer_menu_navigation)
        }

        bind.apply {
            profileBt = profileCvBtt
            ordersBt = orderCvBtt
            chatBt = chatCvBtt
            batteryBt = batteryCvBtt
        }

        args = arguments?.let {
            BuyerMainScreenFragmentArgs.fromBundle(it)
        }

        Log.d("buyerMain","${args?.buyerCode}")

        ordersBt.setOnClickListener {
            val action = BuyerMainScreenFragmentDirections
                .ActionBuyerMainScreenFragmentToOrdersFragment()
            action.buyerCode = args?.buyerCode.toString()

            it.findNavController().navigate(action)
                //.navigate(R.id.OrdersFragment)
        }

        chatBt.setOnClickListener {
            it.findNavController().navigate(R.id.action_buyerMainScreenFragment_to_chatFragment)
        }

        batteryBt.setOnClickListener {
            it.findNavController().navigate(R.id.BatterySearchFragment)
        }

        profileBt.setOnClickListener {
            it.findNavController().navigate(R.id.BuyerProfileFragment)
        }


        return bind.root
    }

}
