package com.cccm.crowingrooster.screens.sales.successful_sales.successful_sale_details

import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.SaleDetails
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.databinding.FragmentSaleDetailsDialogBinding
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass.
 */
class SaleDetailsDialogFragment : DialogFragment() {

    private lateinit var bind: FragmentSaleDetailsDialogBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: SaleDetailsDialogViewModel
//    private lateinit var clientEditT: TextInputEditText
//    private lateinit var emailEditT: TextInputEditText
//    private lateinit var dateEditT: TextInputEditText
//    private lateinit var priceEditT: TextInputEditText
//    private lateinit var paymentEditT: TextInputEditText
    private var successfulSaleList: MutableList<Any> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sale_details_dialog, container, false)
        bind = DataBindingUtil.inflate<FragmentSaleDetailsDialogBinding>(inflater,
            R.layout.fragment_sale_details_dialog, container, false)

        viewModel = ViewModelProvider(this).get(SaleDetailsDialogViewModel::class.java)

        bind.apply {
            nameEt.inputType = InputType.TYPE_NULL
            emailEt.inputType = InputType.TYPE_NULL
            dateEt.inputType = InputType.TYPE_NULL
            priceEt.inputType = InputType.TYPE_NULL
            paymentEt.inputType = InputType.TYPE_NULL
            recyclerView = saleDetailsRv
            //TODO: ClickListeners for the buttons
            closeBtt.setOnClickListener {
                dialog?.dismiss()
            }
        }

        initRecyclerView()

        viewModel.name.observe(viewLifecycleOwner, Observer {
            bind.nameEt.setText(it)
        })
        viewModel.email.observe(viewLifecycleOwner, Observer {
            bind.emailEt.setText(it)
        })
        viewModel.totalQuantity.observe(viewLifecycleOwner, Observer {
            bind.totalQuantityTv.text = it.toString()
        })
        viewModel.price.observe(viewLifecycleOwner, Observer {
            bind.priceEt.setText(it.toString())
        })
        viewModel.payment.observe(viewLifecycleOwner, Observer {
            bind.paymentEt.setText(it)
        })
        viewModel.date.observe(viewLifecycleOwner, Observer {
            bind.dateEt.setText(it)
        })
        viewModel.orders.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter?.notifyDataSetChanged()
        })


//        bind.apply {
//            recyclerView = recyclerViewSsd
//            clientEditT = clientEt!!
//            emailEditT = emailEt
//            dateEditT = dateEt
//            priceEditT = priceEt
//            paymentEditT = paymentEt
//            closeBtt.setOnClickListener {
//                dialog?.dismiss()
//            }
//        }

//        clientEditT.inputType = InputType.TYPE_NULL
//        emailEditT.inputType = InputType.TYPE_NULL
//        dateEditT.inputType = InputType.TYPE_NULL
//        priceEditT.inputType = InputType.TYPE_NULL
//        paymentEditT.inputType = InputType.TYPE_NULL

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

    private fun initRecyclerView() {
        val adapter = object : GenericRecyclerViewAdapter<Any>(viewModel.orders.value!!,requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(
                    view,
                    viewType
                )
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
    }

}
