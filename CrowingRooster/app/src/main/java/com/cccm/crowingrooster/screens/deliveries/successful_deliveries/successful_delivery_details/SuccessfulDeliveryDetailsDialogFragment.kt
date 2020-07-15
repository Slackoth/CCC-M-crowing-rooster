package com.cccm.crowingrooster.screens.deliveries.successful_deliveries.successful_delivery_details

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
import com.cccm.crowingrooster.database.daos.DeliveryDetailsDao
import com.cccm.crowingrooster.database.daos.DeliveryMiniOrdersDao
import com.cccm.crowingrooster.database.entities.DeliveryMiniOrders
import com.cccm.crowingrooster.databinding.FragmentConcludedOrderDetailsDialogBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.repository.delivery.DeliveryDetailsRepository

class SuccessfulDeliveryDetailsDialogFragment : DialogFragment() {

    private lateinit var bind: FragmentConcludedOrderDetailsDialogBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: SuccessfulDeliveryDetailDialogViewModel
    private lateinit var viewModelFactory: SuccessfulDeliveryDetailDialogViewModelFactory
    private lateinit var app: Application
    private lateinit var deliveryDetailsDao: DeliveryDetailsDao
    private lateinit var deliveryMiniOrdersDao: DeliveryMiniOrdersDao
    private lateinit var deliveryDetailsRepository: DeliveryDetailsRepository
    private lateinit var adapter: GenericRecyclerViewAdapter<DeliveryMiniOrders>
    private var deliveryManCode: String? = ""
    private var entregaId: Int? = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_concluded_order_details_dialog,
            container,
            false
        )

        if (arguments != null) {
            arguments?.apply {
                deliveryManCode = getString("code")
                entregaId = getInt("idEntrega")
            }
        }

        app = requireActivity().application
        deliveryDetailsDao = CrowingRoosterDataBase.getInstance(app).deliveryDetailsDao
        deliveryMiniOrdersDao = CrowingRoosterDataBase.getInstance(app).deliveryMiniOrders
        deliveryDetailsRepository = DeliveryDetailsRepository.getInstance(deliveryDetailsDao, deliveryMiniOrdersDao)
        viewModelFactory = SuccessfulDeliveryDetailDialogViewModelFactory(deliveryDetailsRepository,app,deliveryManCode,entregaId)
        viewModel = ViewModelProvider(this,viewModelFactory).get(SuccessfulDeliveryDetailDialogViewModel::class.java)

        bind.apply {
            sddStateEt.inputType = InputType.TYPE_NULL
            sddAddressEt.inputType = InputType.TYPE_NULL
            sddPriceEt.inputType = InputType.TYPE_NULL
            sddPaymentEt.inputType = InputType.TYPE_NULL
            recyclerView = recyclerViewSdd
            sddCloseBtt.setOnClickListener {
                dialog?.dismiss()
            }
        }

        initRecyclerView()

        viewModel.deliveryDetails.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                bind.sddStateEt.setText(it.state)
                bind.sddAddressEt.setText(it.address)
                bind.sddPriceEt.setText(it.price)
                bind.sddPaymentEt.setText(it.payment)
                bind.sddTotalQuantityTv.text = it.totalQuantity.toString()
            }
            else {
                //Log.d("error", "no sirve")
            }
        })

        viewModel.miniOrders.observe(viewLifecycleOwner, Observer {
            if (it != null) {
               // Log.d("mini verga", "$it")
                adapter.setDataSource(it)
            }
            else {
              //  Log.d("error en mini", "no sirve")
            }
        })

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
        adapter = object : GenericRecyclerViewAdapter<DeliveryMiniOrders>(viewModel.miniOrders.value, requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(
                    view,
                    viewType
                )
            }

            override fun getLayoutId(): Int {
                return R.layout.delivery_details_item_layout
            }

            override fun getOnClickLayout(): (List<Any>) -> Unit {
                return {}
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }


}