package com.cccm.crowingrooster.screens.orders.successful_orders

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.order.OrderPreviewDao
import com.cccm.crowingrooster.database.entities.order.OrderPreview
import com.cccm.crowingrooster.databinding.FragmentSuccessfulOrderBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.Order
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.repository.order.OrderPreviewRepository
import com.cccm.crowingrooster.screens.ascending_descending_search.AscDescDialogFragment
import com.cccm.crowingrooster.screens.orders.successful_orders.successful_order_details.SuccessfulOrderDetailsDialogFragment


class SuccessfulOrdersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: SuccessfulOrdersViewModel
    private lateinit var viewModelFactory: SuccessfulOrdersViewModelFactory
    private lateinit var orderPreviewRepository: OrderPreviewRepository
    private lateinit var orderPreviewDao: OrderPreviewDao
    private lateinit var app: Application
    private lateinit var adapter: GenericRecyclerViewAdapter<OrderPreview>
    private var buyerCode: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_successful_orders, container, false)
        val bind = DataBindingUtil.inflate<FragmentSuccessfulOrderBinding>(
            inflater, R.layout.fragment_successful_order,
            container, false
        )

        setHasOptionsMenu(true)

        if (arguments != null) {
            buyerCode = arguments?.getString("code")
        }

        //Log.d("sOrderFrag","${buyerCode}")

        recyclerView = bind.recyclerView

        app = requireActivity().application
        orderPreviewDao = CrowingRoosterDataBase.getInstance(app).orderPreviewDao
        orderPreviewRepository = OrderPreviewRepository.getInstance(orderPreviewDao)

        viewModelFactory = SuccessfulOrdersViewModelFactory(orderPreviewRepository,app,buyerCode)
        viewModel = ViewModelProvider(this,viewModelFactory).get(SuccessfulOrdersViewModel::class.java)

        initRecyclerview()

        viewModel.orderPreviews.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                adapter.setDataSource(it)
            }
            else {
              //  Log.d("sOrderFrag","NOP")
            }
        })

        bind.succesfulDeliveriesSrl.setOnRefreshListener {
            viewModel.refresh()
            bind.succesfulDeliveriesSrl.isRefreshing = false
        }

//        TODO: In case someday we need to change the orientation of the RecyclerView. DO NOT DELETE IT
//        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
//            if (orientation == Configuration.ORIENTATION_LANDSCAPE) LinearLayoutManager.HORIZONTAL
//            else LinearLayoutManager.VERTICAL, false)

        return bind.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.successful_order_more_options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_order_idorder_so, R.id.action_order_date_so -> {
                val dialog =
                    AscDescDialogFragment()
                dialog.show(requireActivity().supportFragmentManager, "AscDescDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerview() {
        //Creating the RecyclerView Adapter
        adapter = object : GenericRecyclerViewAdapter<OrderPreview>(viewModel.orderPreviews.value, requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(view, viewType)
            }

            override fun getOnClickLayout(): (List<Any>) -> Unit {
                val dialog = SuccessfulOrderDetailsDialogFragment()
                val dialogArgs = Bundle()
                return { params ->
                    dialogArgs.putString("buyerCode",buyerCode)
                    dialogArgs.putString("orderId",params[0].toString())
                    dialog.arguments = dialogArgs
                    dialog.show(requireActivity().supportFragmentManager, "OrderDetailsDialog")
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

}








