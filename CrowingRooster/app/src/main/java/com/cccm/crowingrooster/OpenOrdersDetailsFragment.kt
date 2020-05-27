package com.cccm.crowingrooster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cccm.crowingrooster.databinding.FragmentConcludingOrderBinding
import com.cccm.crowingrooster.databinding.FragmentOpenOrdersDetailsBinding

class OpenOrdersDetailsFragment : Fragment() {

    private lateinit var bind: FragmentOpenOrdersDetailsBinding
    private lateinit var concludeButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_open_orders_details, container, false)

        (activity as MainActivity).supportActionBar?.title = "Detalles de entrega"

        bind.apply {
            concludeButton = ooDetailConcludeBtt
        }
        concludeButton.setOnClickListener {
            val dialog = ConcludingOrderFragment()
            dialog.show(requireActivity().supportFragmentManager,"ConcludeDialog")
        }

        return bind.root
    }
}