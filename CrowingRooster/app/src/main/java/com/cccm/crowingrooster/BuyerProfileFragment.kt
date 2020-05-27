package com.cccm.crowingrooster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cccm.crowingrooster.databinding.FragmentBuyerProfileBinding
import kotlinx.android.synthetic.main.activity_main.*

class BuyerProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = DataBindingUtil.inflate<FragmentBuyerProfileBinding>(inflater,
            R.layout.fragment_buyer_profile, container, false)

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = "Perfil".capitalize()
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.buyer_drawer_menu_navigation)
        }

        return bind.root
    }
}