package com.cccm.crowingrooster.screens.orders.ongoing_orders

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.NavGraphDirections
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.order.OrderPreviewDao
import com.cccm.crowingrooster.database.entities.order.OrderPreview
import com.cccm.crowingrooster.databinding.FragmentOngoingOrdersBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.repository.order.OrderPreviewRepository
import com.cccm.crowingrooster.screens.ascending_descending_search.AscDescDialogFragment
import java.lang.Exception


class OngoingOrdersFragment : Fragment () {


    lateinit var recyclerView: RecyclerView
    var orderList: MutableList<Any> = mutableListOf()
    private lateinit var orderPreviewRepository: OrderPreviewRepository
    private lateinit var orderPreviewDao: OrderPreviewDao
    private lateinit var app: Application
    private lateinit var viewModel: OngoingOrdersViewModel
    private lateinit var viewModelFactory: OngoingOrdersViewModelFactory
    private lateinit var adapter: GenericRecyclerViewAdapter<OrderPreview>
    private var buyerCode: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = DataBindingUtil.inflate<FragmentOngoingOrdersBinding>(
            inflater, R.layout.fragment_ongoing_orders,
            container, false
        )
        setHasOptionsMenu(true)

        if (arguments != null) {
            buyerCode = arguments?.getString("code")
        }

        app = requireActivity().application
        orderPreviewDao = CrowingRoosterDataBase.getInstance(app).orderPreviewDao
        orderPreviewRepository = OrderPreviewRepository.getInstance(orderPreviewDao)

        viewModelFactory = OngoingOrdersViewModelFactory(orderPreviewRepository,app,buyerCode)
        viewModel = ViewModelProvider(this,viewModelFactory).get(OngoingOrdersViewModel::class.java)

        recyclerView = bind.recyclerView

        Log.d("onOrderFrag","${buyerCode}")

        initRecyclerview()

        viewModel.orderPreviews.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                adapter.setDataSource(it)
            }
            else {
                Log.d("onOrderFrag","NOP")
            }
        })

        bind.ongoingDeliveriesSrl.setOnRefreshListener {
            viewModel.refresh()
            bind.ongoingDeliveriesSrl.isRefreshing = false
        }

//        TODO: In case someday we need to change the orientation of the RecyclerView. DO NOT DELETE IT
//        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
//            if (orientation == Configuration.ORIENTATION_LANDSCAPE) LinearLayoutManager.HORIZONTAL
//            else LinearLayoutManager.VERTICAL, false)

        return bind.root
    }

    private fun initRecyclerview() {
        //Creating the RecyclerView Adapter
        adapter = object : GenericRecyclerViewAdapter<OrderPreview>(viewModel.orderPreviews.value, requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(view, viewType)
            }

            override fun getOnClickLayout(): (List<Any>) -> Unit {
                return { params ->
                    val globalAction = NavGraphDirections
                        .actionGlobalOngoingOrdersFragmentToOngoingOrderDetailsFragment()
                    globalAction.buyerCode = buyerCode.toString()
                    globalAction.orderId = params[0].toString()

                    try {
                        this@OngoingOrdersFragment.findNavController().navigate(globalAction)
                    }
                    catch (e: Exception) {
                        Log.d("Emensaje","${e.message}")
                    }
                }
            }

            override fun getLayoutId(): Int {
                return R.layout.order_item_layout
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //Adding the divider
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                R.drawable.recyclerview_divider
            )
        )
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ongoing_orders_more_options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return when(item.itemId) {
            R.id.action_order_date, R.id.action_order_idorder -> {
                val dialog =
                    AscDescDialogFragment()
                dialog.show(requireActivity().supportFragmentManager,"AscDescDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}