package com.cccm.crowingrooster.screens.deliveries.successful_deliveries

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.DeliveryPreviewDao
import com.cccm.crowingrooster.database.entities.DeliveryPreview
import com.cccm.crowingrooster.databinding.FragmentConcludedOrdersBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.repository.delivery.DeliveryPreviewRepository
import com.cccm.crowingrooster.screens.deliveries.successful_deliveries.successful_delivery_details.SuccessfulDeliveryDetailsDialogFragment

const val TAG = "HighOrder"

class SuccessfulDeliveriesFragment : Fragment() {
    private lateinit var bind: FragmentConcludedOrdersBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: SuccessfulDeliveriesViewModel
    private lateinit var viewModelFactory: SuccessfulDeliveriesViewModelFactory
    private lateinit var app: Application
    private lateinit var deliveryPreviewDao: DeliveryPreviewDao
    private lateinit var deliveryPreviewRepository: DeliveryPreviewRepository
    private lateinit var adapter: GenericRecyclerViewAdapter<DeliveryPreview>
    private lateinit var refreshLayout: SwipeRefreshLayout
    private var deliveryManCode: String? = ""
    private var idEntrega: Int? = 0

    @SuppressLint("LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_concluded_orders,
            container,
            false
        )

        if (arguments != null) {
            deliveryManCode = arguments?.getString("code")
            idEntrega = arguments?.getInt("idEntrega")
        }

        app = requireActivity().application
        deliveryPreviewDao = CrowingRoosterDataBase.getInstance(app).deliveryPreviewDao
        deliveryPreviewRepository = DeliveryPreviewRepository.getInstance(deliveryPreviewDao)

        viewModelFactory =
            SuccessfulDeliveriesViewModelFactory(
                deliveryPreviewRepository,
                app,
                deliveryManCode
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(SuccessfulDeliveriesViewModel::class.java)

        bind.apply {
            recyclerView = succesfulDeliveriesRv
            refreshLayout = succesfulDeliveriesSrl
            lifecycleOwner = this@SuccessfulDeliveriesFragment
        }

        initRecyclerView()

        viewModel.deliveryPreview.observe(viewLifecycleOwner, Observer {
            adapter.setDataSource(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> bind.deliveryPreviewListProgressBar.visibility = View.VISIBLE
                else -> bind.deliveryPreviewListProgressBar.visibility = View.GONE
            }
        })

        refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        return bind.root
    }

    private fun initRecyclerView() {
        adapter = object : GenericRecyclerViewAdapter<DeliveryPreview>(viewModel.deliveryPreview.value, requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(
                    view,
                    viewType
                )
            }

            override fun getOnClickLayout(): (List<Any>) -> Unit {
                val dialog =
                    SuccessfulDeliveryDetailsDialogFragment()
                val args = Bundle()
                return { params ->
                    args.putString("code", deliveryManCode)
                    args.putInt("idEntrega", params[1].toString().toInt())
                    dialog.arguments = args
                    dialog.show(requireActivity().supportFragmentManager, "SuccessfulDeliveryDetailsDialog")
                }
            }

            override fun getLayoutId(): Int {
                return R.layout.delivery_item_layout
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                R.drawable.recyclerview_divider
            )
        )
        recyclerView.adapter = adapter
    }
}