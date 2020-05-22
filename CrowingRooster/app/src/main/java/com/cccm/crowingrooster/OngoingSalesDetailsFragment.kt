package com.cccm.crowingrooster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.cccm.crowingrooster.databinding.FragmentOngoingSalesBinding
import com.cccm.crowingrooster.databinding.FragmentOngoingSalesDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class OngoingSalesDetailsFragment : Fragment() {

    private lateinit var bind: FragmentOngoingSalesDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_ongoing_sales_details, container, false)
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_ongoing_sales_details, container, false)

        return bind.root
    }

}
