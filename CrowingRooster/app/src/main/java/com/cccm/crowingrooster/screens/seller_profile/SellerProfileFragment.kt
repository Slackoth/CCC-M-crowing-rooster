package com.cccm.crowingrooster.screens.seller_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentSellerProfileBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class SellerProfileFragment : Fragment() {
    private lateinit var bind: FragmentSellerProfileBinding
    private lateinit var viewModel: SellerProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_seller_profile, container, false)
        bind = DataBindingUtil.inflate<FragmentSellerProfileBinding>(inflater,
            R.layout.fragment_seller_profile,
        container, false)

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.profile).capitalize()
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.seller_drawer_menu_navigation)
        }

        viewModel = ViewModelProvider(this).get(SellerProfileViewModel::class.java)

        bind.sellerProfileViewModel = viewModel

        viewModel.code.observe(viewLifecycleOwner, Observer {
            bind.codeDescTv.text = it
        })
        viewModel.name.observe(viewLifecycleOwner, Observer {
            bind.nameDescTv.text = it
        })
        viewModel.phone.observe(viewLifecycleOwner, Observer {
            bind.telDescTv.text = it
        })
        viewModel.email.observe(viewLifecycleOwner, Observer {
            bind.emailDescTv.text = it
        })

        return bind.root
    }

}
