package com.cccm.crowingrooster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
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
            chatBt = chatCvBtt
            batteryBt = batteryCvBtt
        }

        ordersBt.setOnClickListener {
            it.findNavController().navigate(R.id.OrdersFragment)
        }

        chatBt.setOnClickListener {
            it.findNavController().navigate(R.id.chatFragment)
        }

        batteryBt.setOnClickListener {
            it.findNavController().navigate(R.id.SearchFragment)
        }


        return bind.root
    }

}
