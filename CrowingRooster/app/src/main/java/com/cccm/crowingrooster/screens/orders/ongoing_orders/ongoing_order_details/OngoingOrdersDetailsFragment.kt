package com.cccm.crowingrooster.screens.orders.ongoing_orders.ongoing_order_details

import android.app.Application
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.SaleDetailsDao
import com.cccm.crowingrooster.database.daos.SaleMiniOrdersDao
import com.cccm.crowingrooster.database.daos.order.OrderDetailsDao
import com.cccm.crowingrooster.database.daos.order.OrderMiniOrderDao
import com.cccm.crowingrooster.database.entities.order.OrderMiniOrder
import com.cccm.crowingrooster.databinding.FragmentOngoingOrdersDetailsBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.OrderDetails
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.repository.order.OrderDetailsRepository
import com.cccm.crowingrooster.network.repository.seller.SaleDetailsRepository
import com.cccm.crowingrooster.screens.sales.ongoing_sales.ongoing_sale_details.OngoingSalesDetailsFragmentArgs
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*

class OngoingOrdersDetailsFragment : Fragment() {

    private lateinit var bind: FragmentOngoingOrdersDetailsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GenericRecyclerViewAdapter<OrderMiniOrder>
    private var args: OngoingOrdersDetailsFragmentArgs? = null
    private lateinit var app: Application
    private lateinit var orderDetailsDao: OrderDetailsDao
    private lateinit var orderMiniOrdersDao: OrderMiniOrderDao
    private lateinit var orderDetailsRepository: OrderDetailsRepository
    private lateinit var viewModel: OngoingOrderDetailsViewModel
    private lateinit var viewModelFactory: OngoingOrderDetailsViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_ongoing_orders_details, container, false)

        args = arguments?.let { OngoingOrdersDetailsFragmentArgs.fromBundle(it) }

        Log.d("ongoingOrderDetails","${args?.buyerCode}-${args?.orderId}")

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(
                R.string.details
            ).capitalize()
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.seller_drawer_menu_navigation)
        }

        bind.apply {
            recyclerView = recyclerViewOsd
            sellerEt.inputType = InputType.TYPE_NULL
            emailEt.inputType = InputType.TYPE_NULL
            dateEt.inputType = InputType.TYPE_NULL
        }

        app = requireActivity().application
        orderDetailsDao = CrowingRoosterDataBase.getInstance(app).orderDetailsDao
        orderMiniOrdersDao = CrowingRoosterDataBase.getInstance(app).orderMiniOrdersDao
        orderDetailsRepository = OrderDetailsRepository.getInstance(orderDetailsDao,orderMiniOrdersDao)

        viewModelFactory = OngoingOrderDetailsViewModelFactory(orderDetailsRepository,app,args?.buyerCode ?: "",args?.orderId ?: "")
        viewModel = ViewModelProvider(this,viewModelFactory).get(OngoingOrderDetailsViewModel::class.java)

        viewModel.orderDetails.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                bind.sellerEt.setText(it.name)
                bind.emailEt.setText(it.email)
                bind.totalQuantityTv.text = it.total
                bind.dateEt.setText(it.date)
            }
            else {
                Log.d("sOrderDetails","NO SIRVIO")
            }
        })
        viewModel.orderMiniOrder.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.d("MINIORDER","$it")
                adapter.setDataSource(it)
            }
            else {
                Log.d("ERROR","NO SIRVE")
            }

        })


        initRecyclerview()

        return bind.root
    }

    private fun initRecyclerview() {
        adapter= object : GenericRecyclerViewAdapter<OrderMiniOrder>(viewModel.orderMiniOrder.value,requireContext()) {
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

}