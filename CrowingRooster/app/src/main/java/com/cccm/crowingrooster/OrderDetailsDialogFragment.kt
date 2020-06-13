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
import com.cccm.crowingrooster.databinding.FragmentOrdersDetailsDialogBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.OrderDetails
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.google.android.material.textfield.TextInputEditText

class OrderDetailsDialogFragment : DialogFragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var clientEditT: TextInputEditText
    private lateinit var emailEditT: TextInputEditText
    private lateinit var dateEditT: TextInputEditText
    private lateinit var priceEditT: TextInputEditText
    private lateinit var paymentEditT: TextInputEditText
    private var successfulOrderList: MutableList<Any> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sale_details_dialog, container, false)
        val bind = DataBindingUtil.inflate<FragmentOrdersDetailsDialogBinding>(inflater,
            R.layout.fragment_orders_details_dialog, container, false)

        bind.apply {
            recyclerView = recyclerViewSsd
            clientEditT = sellerEt!!
            emailEditT = emailEt
            dateEditT = dateEt
            priceEditT = priceEt
            paymentEditT = paymentEt
            closeBtt.setOnClickListener {
                dialog?.dismiss()
            }
        }

        clientEditT.inputType = InputType.TYPE_NULL
        emailEditT.inputType = InputType.TYPE_NULL
        dateEditT.inputType = InputType.TYPE_NULL
        priceEditT.inputType = InputType.TYPE_NULL
        paymentEditT.inputType = InputType.TYPE_NULL


        successfulOrderList.addAll(
            listOf(
                OrderDetails(
                    10,
                    "20F-Derecha-Azul"
                ),
                OrderDetails(
                    15,
                    "21D-Izquierda-Amarilla"
                ),
                OrderDetails(
                    2,
                    "22E-Derecha-Amarilla"
                ),
                OrderDetails(
                    30,
                    "23Q-Izquierda-Azul"
                ),
                OrderDetails(
                    6,
                    "24P-Derecha-Azul"
                )
            )
        )


        val adapter = object : GenericRecyclerViewAdapter<Any>(successfulOrderList,requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(view,viewType)
            }

            override fun getLayoutId(): Int {
                return R.layout.order_details_item
            }

            override fun getOnClickLayout(): () -> Unit {
                return {}
            }

        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),R.drawable.recyclerview_divider))
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