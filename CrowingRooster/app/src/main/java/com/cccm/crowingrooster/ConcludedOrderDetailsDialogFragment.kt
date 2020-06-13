package com.cccm.crowingrooster

import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.databinding.FragmentConcludedOrderDetailsDialogBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.SaleDetails
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.google.android.material.textfield.TextInputEditText

class ConcludedOrderDetailsDialogFragment : DialogFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addressEditT: TextInputEditText
    private lateinit var priceEditT: TextInputEditText
    private lateinit var paymentEditT: TextInputEditText
    private lateinit var dateEditT: TextInputEditText
    private lateinit var timeEditT: TextInputEditText
    private lateinit var clientEditT: TextInputEditText
    private var concludedOrdersList: MutableList<Any> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = DataBindingUtil.inflate<FragmentConcludedOrderDetailsDialogBinding>(
            inflater,
            R.layout.fragment_concluded_order_details_dialog,
            container,
            false
        )

        bind.apply {
            recyclerView = recyclerViewCod
            addressEditT = codAddressEt
            priceEditT = codPriceEt
            paymentEditT = codPaymentEt
            dateEditT = codDateEt
            timeEditT = codTimeEt
            clientEditT = codClientEt
            codCloseBtt.setOnClickListener {
                dialog?.dismiss()
            }
        }

        addressEditT.inputType = InputType.TYPE_NULL
        priceEditT.inputType = InputType.TYPE_NULL
        paymentEditT.inputType = InputType.TYPE_NULL
        dateEditT.inputType = InputType.TYPE_NULL
        timeEditT.inputType = InputType.TYPE_NULL
        clientEditT.inputType = InputType.TYPE_NULL

        concludedOrdersList.addAll(
            listOf(
                SaleDetails(
                    10,
                    "20F-Derecha-Azul"
                ),
                SaleDetails(
                    15,
                    "21D-Izquierda-Amarilla"
                ),
                SaleDetails(
                    2,
                    "22E-Derecha-Amarilla"
                ),
                SaleDetails(
                    30,
                    "23Q-Izquierda-Azul"
                ),
                SaleDetails(
                    6,
                    "24P-Derecha-Azul"
                )
            )
        )

        val adapter = object : GenericRecyclerViewAdapter<Any>(concludedOrdersList, requireContext()) {

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(view, viewType)
            }

            override fun getLayoutId(): Int {
                return R.layout.sale_details_item_layout
            }

            override fun getOnClickLayout(): () -> Unit {
                return {}
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter = adapter

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