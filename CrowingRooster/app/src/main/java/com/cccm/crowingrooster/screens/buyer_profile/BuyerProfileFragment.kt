package com.cccm.crowingrooster.screens.buyer_profile

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
//import com.cccm.crowingrooster.BuyerProfileFragmentArgs
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.SellerDao
import com.cccm.crowingrooster.database.daos.order.BuyerDao
import com.cccm.crowingrooster.databinding.FragmentBuyerProfileBinding
import com.cccm.crowingrooster.network.repository.order.BuyerProfileRepository
import com.cccm.crowingrooster.network.repository.seller.SellerRepository
import com.cccm.crowingrooster.screens.seller_main_screen.SellerMainScreenArgs
import com.cccm.crowingrooster.screens.seller_profile.SellerProfileViewModel
import com.cccm.crowingrooster.screens.seller_profile.SellerProfileViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class BuyerProfileFragment : Fragment() {
    private lateinit var app: Application
    private lateinit var buyerProfileRepository: BuyerProfileRepository
    private lateinit var viewModelFactory: BuyerProfileViewModelFactory
    private lateinit var viewModel: BuyerProfileViewModel
    private lateinit var buyerDao: BuyerDao
    private var args: BuyerProfileFragmentArgs? = null

    @SuppressLint("LogNotTimber")
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

        args = arguments?.let {
            BuyerProfileFragmentArgs.fromBundle(it)
        }

       //Log.d("buyerproooooofile","${args?.buyerCode}"   + "no  tira datqaaaaaaa")

        app = requireActivity().application
        buyerDao = CrowingRoosterDataBase.getInstance(app).buyerDao
        buyerProfileRepository = BuyerProfileRepository.getInstance(buyerDao)

        viewModelFactory = BuyerProfileViewModelFactory(buyerProfileRepository,app,args!!.buyerCode )
        viewModel = ViewModelProvider(this,viewModelFactory).get(BuyerProfileViewModel::class.java)

        viewModel.buyer.observe(viewLifecycleOwner, Observer {
            if (it != null) {
          //      Log.d("buyerPTM","$it")
                bind.buyerNameDescTv.text = it.name
                bind.buyerCompDescTv.text = it.company
                bind.buyerDuiDescTv.text = it.dui
                bind.buyerEmailDescTv.text = it.email
                bind.buyerRealPhoneTv.text = it.phone
                Glide.with(bind.buyerProfileCiv.context).load(it.img).into(bind.buyerProfileCiv)
            }
            else {
            //    Log.d("buProfileFragment","NO SE QUE PASO")
            }
        })

        return bind.root
    }
}