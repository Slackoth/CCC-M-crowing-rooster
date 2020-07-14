package com.cccm.crowingrooster.screens.deliveries.ongoing_deliveries.ongoing_deliveries_details

import android.app.Application
import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.NavigationDirections
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.DeliveryDetailsDao
import com.cccm.crowingrooster.database.daos.DeliveryMiniOrdersDao
import com.cccm.crowingrooster.database.entities.DeliveryMiniOrders
import com.cccm.crowingrooster.databinding.FragmentOpenOrdersDetailsBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.body.ConfirmDeliveryBody
import com.cccm.crowingrooster.network.repository.delivery.DeliveryDetailsRepository
import com.cccm.crowingrooster.screens.deliveries.ongoing_deliveries.ConcludingOrderFragment
import com.google.android.material.textfield.TextInputEditText

class OngoingDeliveryDetailsFragment : DialogFragment() {

    private lateinit var bind: FragmentOpenOrdersDetailsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: OngoingDeliveryDetailsViewModel
    private lateinit var viewModelFactory: OngoingDeliveryDetailsViewModelFactory
    private lateinit var app: Application
    private lateinit var deliveryDetailsDao: DeliveryDetailsDao
    private lateinit var deliveryMiniOrdersDao: DeliveryMiniOrdersDao
    private lateinit var deliveryDetailsRepository: DeliveryDetailsRepository
    private lateinit var adapter: GenericRecyclerViewAdapter<DeliveryMiniOrders>
    private var deliveryManCode: String? = ""
    private var deliveryId: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_open_orders_details,
            container,
            false)

        if (arguments != null) {
            arguments?.apply {
                deliveryManCode = getString("code")
                deliveryId = getInt("deliveryId")
            }
        }

        bind.apply {
            sddStateEt.inputType = InputType.TYPE_NULL
            sddAddressEt.inputType = InputType.TYPE_NULL
            sddPriceEt.inputType = InputType.TYPE_NULL
            sddPaymentEt.inputType = InputType.TYPE_NULL
            recyclerView = ongoingOrderDetailsRv
            sddCloseBtt.setOnClickListener {
                dialog?.dismiss()
            }
            confirmBtt.setOnClickListener {
                var price = bind.sddPriceEt.text.toString()
                price= price.replace("$","")

                var ConfirmDeliveryBody=ConfirmDeliveryBody(price.toDouble(), bind.sddPaymentEt.text.toString() )



                viewModel.confirmDelivery(deliveryManCode,deliveryId, ConfirmDeliveryBody)

                Toast.makeText(requireContext(),"Se ha confirmado la entrega",Toast.LENGTH_SHORT).show()
                val globalAction = NavigationDirections
                    .actionGlobalOngoingDeliveryDetailsFragmentToDeliveriesFragment()
                globalAction.deliveryManCode = deliveryManCode.toString()
                this@OngoingDeliveryDetailsFragment.findNavController().navigate(globalAction)
                dialog?.dismiss()
            }
        }

        app = requireActivity().application
        deliveryDetailsDao = CrowingRoosterDataBase.getInstance(app).deliveryDetailsDao
        deliveryMiniOrdersDao = CrowingRoosterDataBase.getInstance(app).deliveryMiniOrders
        deliveryDetailsRepository = DeliveryDetailsRepository.getInstance(deliveryDetailsDao, deliveryMiniOrdersDao)

        viewModelFactory = OngoingDeliveryDetailsViewModelFactory(deliveryDetailsRepository,app,deliveryManCode,deliveryId)
        viewModel = ViewModelProvider(this,viewModelFactory).get(OngoingDeliveryDetailsViewModel::class.java)

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
                Log.d("error", "no sirve")
            }
        })

        viewModel.miniOrders.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.d("mini dos", "$it")
                adapter.setDataSource(it)
            }
            else {
                Log.d("mini dosdos", "no sirve")
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