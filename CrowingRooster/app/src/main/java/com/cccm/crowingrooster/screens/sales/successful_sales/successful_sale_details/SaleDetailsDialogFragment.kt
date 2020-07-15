package com.cccm.crowingrooster.screens.sales.successful_sales.successful_sale_details

import android.app.Application
import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.SaleMiniOrdersDao
import com.cccm.crowingrooster.database.daos.SaleDetailsDao
import com.cccm.crowingrooster.database.entities.SaleMiniOrders
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.databinding.FragmentSaleDetailsDialogBinding
import com.cccm.crowingrooster.network.repository.seller.SaleDetailsRepository

/**
 * A simple [Fragment] subclass.
 */
class SaleDetailsDialogFragment : DialogFragment() {

    private lateinit var bind: FragmentSaleDetailsDialogBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: SaleDetailsDialogViewModel
    private lateinit var viewModelFactory: SaleDetailsDialogViewModelFactory
    private lateinit var app: Application
    private lateinit var saleDetailsDao: SaleDetailsDao
    private lateinit var saleMiniOrdersDao: SaleMiniOrdersDao
    private lateinit var saleDetailsRepository: SaleDetailsRepository
    private lateinit var adapter: GenericRecyclerViewAdapter<SaleMiniOrders>
    private var sellerCode: String? = ""
    private var orderId: String? = ""
    private var saleId: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sale_details_dialog, container, false)
        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_sale_details_dialog, container, false)

        if (arguments != null) {
            arguments?.apply {
                sellerCode = getString("sellerCode")
                orderId = getString("orderId")
                saleId = getString("saleId")
            }
        }

        Log.d("ongoingsaledetails","${sellerCode}-${orderId}-${saleId}")

        app = requireActivity().application
        saleDetailsDao = CrowingRoosterDataBase.getInstance(app).saleDetailsDao
        saleMiniOrdersDao = CrowingRoosterDataBase.getInstance(app).saleMiniOrdersDao
        saleDetailsRepository = SaleDetailsRepository.getInstance(saleDetailsDao,saleMiniOrdersDao)

        viewModelFactory = SaleDetailsDialogViewModelFactory(saleDetailsRepository,app,sellerCode,orderId,saleId)
        viewModel = ViewModelProvider(this,viewModelFactory).get(SaleDetailsDialogViewModel::class.java)

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

        viewModel.saleDetails.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                bind.nameEt.setText(it.name)
                bind.emailEt.setText(it.email)
                bind.dateEt.setText(it.date)
                bind.priceEt.setText(it.price)
                bind.paymentEt.setText(it.payment)
                bind.totalQuantityTv.text = it.totalQuantity.toString()
            }
            else {
             //   Log.d("ERROR","NO SIRVE")
            }

        })

        viewModel.miniOrders.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                //Log.d("MINI","$it")
                adapter.setDataSource(it)
            }
            else {
              //  Log.d("ERROR","NO SIRVE")
            }

        })

//        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
//            when(it) {
//                true -> bind.salePreviewListProgressBar.visibility = View.VISIBLE
//                else -> bind.salePreviewListProgressBar.visibility = View.GONE
//            }
//        })

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
        adapter = object : GenericRecyclerViewAdapter<SaleMiniOrders>(viewModel.miniOrders.value,requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(
                    view,
                    viewType
                )
            }

            override fun getLayoutId(): Int {
                return R.layout.sale_details_item_layout
            }

            override fun getOnClickLayout(): (List<Any>) -> Unit {
                return {}
            }

        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),R.drawable.recyclerview_divider))
        recyclerView.adapter = adapter
    }

}
