package com.cccm.crowingrooster.screens.seller_profile

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.SellerDao
import com.cccm.crowingrooster.databinding.FragmentSellerProfileBinding
import com.cccm.crowingrooster.network.repository.seller.SellerRepository
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class SellerProfileFragment : Fragment() {
    private lateinit var bind: FragmentSellerProfileBinding
    private lateinit var app: Application
    private lateinit var sellerRepository: SellerRepository
    private lateinit var viewModelFactory: SellerProfileViewModelFactory
    private lateinit var viewModel: SellerProfileViewModel
    private lateinit var sellerDao: SellerDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_seller_profile, container, false)
        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_seller_profile,
        container, false)

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.profile).capitalize()
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.seller_drawer_menu_navigation)
        }

        app = requireActivity().application
        sellerDao = CrowingRoosterDataBase.getInstance(app).sellerDao
        sellerRepository = SellerRepository.getInstance(sellerDao)


        viewModelFactory = SellerProfileViewModelFactory(sellerRepository,app)
        viewModel = ViewModelProvider(this,viewModelFactory).get(SellerProfileViewModel::class.java)

        viewModel.seller.observe(viewLifecycleOwner, Observer {
            bind.codeDescTv.text = it.code
            bind.nameDescTv.text = it.name
            bind.telDescTv.text = it.phone
            bind.emailDescTv.text = it.email
            Glide.with(bind.profileCiv.context).load(it.img).into(bind.profileCiv)
        })
        //bind.sellerProfileViewModel = viewModel

        return bind.root
    }

}
