package com.cccm.crowingrooster

import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.databinding.FragmentSaleDetailsDialogBinding
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass.
 */
class SaleDetailsDialogFragment : DialogFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var clientEditT: TextInputEditText
    private lateinit var emailEditT: TextInputEditText
    private lateinit var dateEditT: TextInputEditText
    private lateinit var priceEditT: TextInputEditText
    private lateinit var paymentEditT: TextInputEditText
    private var successfulSaleList: MutableList<Any> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sale_details_dialog, container, false)
        val bind = DataBindingUtil.inflate<FragmentSaleDetailsDialogBinding>(inflater,
        R.layout.fragment_sale_details_dialog, container, false)

        bind.apply {
            recyclerView = recyclerViewSsd
            clientEditT = clientEt!!
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


        successfulSaleList.addAll(
            listOf(
                SaleDetails(10, "20F-Derecha-Azul"),
                SaleDetails(15, "21D-Izquierda-Amarilla"),
                SaleDetails(2, "22E-Derecha-Amarilla"),
                SaleDetails(30, "23Q-Izquierda-Azul"),
                SaleDetails(6, "24P-Derecha-Azul")
            )
        )


        val adapter = object : GenericRecyclerViewAdapter<Any>(successfulSaleList,requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(view,viewType)
            }

            override fun getLayoutId(): Int {
                return R.layout.sale_details_item_layout
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
