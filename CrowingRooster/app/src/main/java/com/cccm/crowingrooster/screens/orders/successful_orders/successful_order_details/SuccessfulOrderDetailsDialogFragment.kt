package com.cccm.crowingrooster.screens.orders.successful_orders.successful_order_details

import android.app.Application
import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.order.OrderDetailsDao
import com.cccm.crowingrooster.database.daos.order.OrderMiniOrderDao
import com.cccm.crowingrooster.database.entities.order.OrderMiniOrder
import com.cccm.crowingrooster.databinding.FragmentOrdersDetailsDialogBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.OrderDetails
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.repository.order.OrderDetailsRepository
import com.google.android.material.textfield.TextInputEditText

class SuccessfulOrderDetailsDialogFragment : DialogFragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GenericRecyclerViewAdapter<OrderMiniOrder>
    private lateinit var app: Application
    private lateinit var orderDetailsDao: OrderDetailsDao
    private lateinit var orderMiniOrderDao: OrderMiniOrderDao
    private lateinit var orderDetailsRepository: OrderDetailsRepository
    private lateinit var viewModel: SuccessfulOrderDetailsViewModel
    private lateinit var viewModelFactory: SuccessfulOrderDetailsViewModelFactory
    private var successfulOrderList: MutableList<Any> = mutableListOf()
    private var buyerCode: String? = ""
    private var orderId: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sale_details_dialog, container, false)
        val bind = DataBindingUtil.inflate<FragmentOrdersDetailsDialogBinding>(inflater,
            R.layout.fragment_orders_details_dialog, container, false)

        if (arguments != null) {
            arguments?.apply {
                buyerCode = arguments?.getString("buyerCode")
                orderId= arguments?.getString("orderId")
            }
        }

        Log.d("sOrderDetFrag","${buyerCode} - $orderId")

        app = requireActivity().application
        orderDetailsDao = CrowingRoosterDataBase.getInstance(app).orderDetailsDao
        orderMiniOrderDao = CrowingRoosterDataBase.getInstance(app).orderMiniOrdersDao
        orderDetailsRepository = OrderDetailsRepository.getInstance(orderDetailsDao,orderMiniOrderDao)

        viewModelFactory = SuccessfulOrderDetailsViewModelFactory(orderDetailsRepository,app,buyerCode,orderId)
        viewModel = ViewModelProvider(this,viewModelFactory).get(SuccessfulOrderDetailsViewModel::class.java)

        bind.apply {
            recyclerView = recyclerViewSsd
            sellerEt.inputType = InputType.TYPE_NULL
            emailEt.inputType = InputType.TYPE_NULL
            priceEt.inputType = InputType.TYPE_NULL
            dateEt.inputType = InputType.TYPE_NULL
            paymentEt.inputType = InputType.TYPE_NULL
            closeBtt.setOnClickListener {
                dialog?.dismiss()
            }
        }

        initRecyclerview()

        viewModel.orderDetails.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                bind.sellerEt.setText(it.name)
                bind.emailEt.setText(it.email)
                bind.totalQuantityTv.text = it.total
                bind.dateEt.setText(it.date)
                bind.priceEt.setText(it.price)
                bind.paymentEt.setText(it.payment)
            }
            else {
                Log.d("sOrderDetails","NO SIRVIO")
            }
        })
        viewModel.miniOrders.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.d("MINIORDER","$it")
                adapter.setDataSource(it)
            }
            else {
                Log.d("ERROR","NO SIRVE")
            }

        })

        return bind.root
    }

    private fun initRecyclerview() {
        adapter = object : GenericRecyclerViewAdapter<OrderMiniOrder>(/*viewModel.miniOrders.value*/
            listOf(),requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(view,viewType)
            }

            override fun getLayoutId(): Int {
                return R.layout.order_details_item
            }

            override fun getOnClickLayout(): (List<Any>) -> Unit {
                return {}
            }

        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),R.drawable.recyclerview_divider))
        recyclerView.adapter = adapter
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