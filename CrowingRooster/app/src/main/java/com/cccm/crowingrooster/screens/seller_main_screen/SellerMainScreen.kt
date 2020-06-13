package com.cccm.crowingrooster.screens.seller_main_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentSellerMainScreenBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class SellerMainScreen : Fragment() {

    lateinit var profileBt: CardView
    lateinit var salesBt: CardView
    lateinit var clientsBt: CardView
    lateinit var chatBt: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_seller_main_screen, container, false)
        val bind = DataBindingUtil.inflate<FragmentSellerMainScreenBinding>(inflater,
            R.layout.fragment_seller_main_screen,
        container, false)

        //(activity as MainActivity).showTopBar()
        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.main_menu)
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.seller_drawer_menu_navigation)
        }

        bind.apply {
            profileBt = profileCvBtt
            salesBt = salesCvBtt
            clientsBt = clientsCvBtt
            chatBt = chatCvBtt
        }

        profileBt.setOnClickListener {
            it.findNavController().navigate(R.id.action_sellerMainScreen_to_sellerProfileFragment)
        }
        salesBt.setOnClickListener {
            it.findNavController().navigate(R.id.action_sellerMainScreen_to_salesFragment)
        }
        clientsBt.setOnClickListener {
            it.findNavController().navigate(R.id.action_sellerMainScreen_to_sellerClientListFragment)
        }
        chatBt.setOnClickListener(){
            it.findNavController().navigate(R.id.action_sellerMainScreen_to_chatFragment)
        }

        return  bind.root
    }




}
