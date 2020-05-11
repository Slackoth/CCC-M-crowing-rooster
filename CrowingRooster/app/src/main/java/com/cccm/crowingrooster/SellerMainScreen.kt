package com.cccm.crowingrooster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.cccm.crowingrooster.databinding.FragmentSellerMainScreenBinding
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.top_app_bar_main_screen.*

/**
 * A simple [Fragment] subclass.
 */
class SellerMainScreen : Fragment() {
    lateinit var materialToolbar: MaterialToolbar
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_seller_main_screen, container, false)
        val bind = DataBindingUtil.inflate<FragmentSellerMainScreenBinding>(inflater, R.layout.fragment_seller_main_screen,
        container, false)

        //(activity as MainActivity).showTopBar()
        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.main_menu)
        }
        return  bind.root
    }




}
