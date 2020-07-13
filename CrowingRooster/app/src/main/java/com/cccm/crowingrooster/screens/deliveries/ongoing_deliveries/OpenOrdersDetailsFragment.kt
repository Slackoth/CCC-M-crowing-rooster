package com.cccm.crowingrooster.screens.deliveries.ongoing_deliveries

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentOpenOrdersDetailsBinding
import com.google.android.material.textfield.TextInputEditText

class OpenOrdersDetailsFragment : Fragment() {

    private lateinit var bind: FragmentOpenOrdersDetailsBinding
    private lateinit var clientEditT: TextInputEditText
    private lateinit var dateEditT: TextInputEditText
    private lateinit var addressEditT: TextInputEditText
    private lateinit var modelEditT: TextInputEditText
    private lateinit var quantityEditT: TextInputEditText
    private lateinit var concludeButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_open_orders_details,
            container,
            false
        )

        (activity as MainActivity).supportActionBar?.title = "Detalles de entrega"

        bind.apply {
            concludeButton = ooDetailConcludeBtt
            clientEditT = ooDetailClientEt
            dateEditT = ooDetailDateEt
            addressEditT = ooDetailAddressEt
            modelEditT = ooDetailModelEt
            quantityEditT = ooDetailQuantityEt
        }

        clientEditT.inputType = InputType.TYPE_NULL
        dateEditT.inputType = InputType.TYPE_NULL
        addressEditT.inputType = InputType.TYPE_NULL
        modelEditT.inputType = InputType.TYPE_NULL
        quantityEditT.inputType = InputType.TYPE_NULL

        concludeButton.setOnClickListener {
            val dialog =
                ConcludingOrderFragment()
            dialog.show(requireActivity().supportFragmentManager, "ConcludeDialog")
        }

        return bind.root
    }
}