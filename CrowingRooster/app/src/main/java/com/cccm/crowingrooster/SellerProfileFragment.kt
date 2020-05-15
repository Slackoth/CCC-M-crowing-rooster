package com.cccm.crowingrooster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.cccm.crowingrooster.databinding.FragmentSellerProfileBinding

/**
 * A simple [Fragment] subclass.
 */
class SellerProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_seller_profile, container, false)
        val bind = DataBindingUtil.inflate<FragmentSellerProfileBinding>(inflater, R.layout.fragment_seller_profile,
        container, false)

        (activity as MainActivity).supportActionBar?.title = getString(R.string.profile).capitalize()
        return bind.root
    }

}
