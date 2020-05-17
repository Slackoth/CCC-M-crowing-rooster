package com.cccm.crowingrooster

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.cccm.crowingrooster.databinding.FragmentSaleDetailsDialogBinding

/**
 * A simple [Fragment] subclass.
 */
class SaleDetailsDialogFragment : DialogFragment() {

    companion object {
        const val TAG: String = "SaleDetails"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sale_details_dialog, container, false)
        val bind = DataBindingUtil.inflate<FragmentSaleDetailsDialogBinding>(inflater,
        R.layout.fragment_sale_details_dialog, container, false)

        bind.apply {
            closeBtt.setOnClickListener {
                dialog?.dismiss()
            }
        }

        return bind.root
    }

    override fun onStart() {
        super.onStart()
        var dialog: Dialog = requireDialog()
        dialog?.apply {
            var width = ViewGroup.LayoutParams.MATCH_PARENT
            var height = ViewGroup.LayoutParams.MATCH_PARENT
            this.window?.setLayout(width, height)
        }
    }

}
