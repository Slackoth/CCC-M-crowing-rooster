package com.cccm.crowingrooster.screens.sales.ongoing_sales.confirm_sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentConfirmSaleBinding

class ConfirmSaleFragment: DialogFragment() {

    lateinit var bind: FragmentConfirmSaleBinding
    lateinit var spinner: Spinner
    lateinit var confirmTextView: TextView
    lateinit var cancelTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_confirm_sale, container,false)

        bind.apply {
            spinner = paymentSp
            confirmTextView = confirmTv
            cancelTextView = cancelTv
        }

        ArrayAdapter.createFromResource(requireContext(),
            R.array.payment_array,
            android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = it
        }

        cancelTextView.setOnClickListener{
            dialog?.dismiss()
        }
        confirmTextView.setOnClickListener{

        }

        return bind.root
    }
}