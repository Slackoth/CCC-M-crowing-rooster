package com.cccm.crowingrooster.screens.deliveries.ongoing_deliveries

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.DeliveryPreviewDao
import com.cccm.crowingrooster.database.entities.DeliveryPreview
import com.cccm.crowingrooster.databinding.FragmentOpenOrdersBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.OpenOrder
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.repository.delivery.DeliveryPreviewRepository
import com.cccm.crowingrooster.screens.deliveries.ongoing_deliveries.ongoing_deliveries_details.OngoingDeliveryDetailsFragment
import com.cccm.crowingrooster.screens.deliveries.successful_deliveries.successful_delivery_details.SuccessfulDeliveryDetailsDialogFragment

class OngoingDeliveryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: OngoingDeliveryViewModel
    private lateinit var viewModelFactory: OngoingDeliveryViewModelFactory
    private lateinit var app: Application
    private lateinit var deliveryPreviewDao: DeliveryPreviewDao
    private lateinit var deliveryPreviewRepository: DeliveryPreviewRepository
    private lateinit var adapter: GenericRecyclerViewAdapter<DeliveryPreview>
    private var deliveryManCode: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = DataBindingUtil.inflate<FragmentOpenOrdersBinding>(
            inflater,
            R.layout.fragment_open_orders,
            container,
            false
        )

        if (arguments != null) {
            deliveryManCode = arguments?.getString("code")
        }

        app = requireActivity().application
        deliveryPreviewDao = CrowingRoosterDataBase.getInstance(app).deliveryPreviewDao
        deliveryPreviewRepository = DeliveryPreviewRepository.getInstance(deliveryPreviewDao)

        viewModelFactory = OngoingDeliveryViewModelFactory(deliveryPreviewRepository,app,deliveryManCode ?: "")
        viewModel = ViewModelProvider(this,viewModelFactory).get(OngoingDeliveryViewModel::class.java)

        bind.apply {
            recyclerView = recyclerViewOo
            lifecycleOwner = this@OngoingDeliveryFragment
        }

        initRecyclerview()

        viewModel.deliveryPreview.observe(viewLifecycleOwner, Observer {
            adapter.setDataSource(it)
        })

        bind.ongoingOrderSrl.setOnRefreshListener {
            viewModel.refresh()
            bind.ongoingOrderSrl.isRefreshing = false
        }

        setHasOptionsMenu(true)

        recyclerView = bind.recyclerViewOo

        return bind.root
    }

    private fun initRecyclerview() {
        adapter = object : GenericRecyclerViewAdapter<DeliveryPreview>(viewModel.deliveryPreview.value, requireContext()) {

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {

                return ViewHolderFactory.bindView(view, viewType)
            }

            override fun getLayoutId(): Int {

                return R.layout.delivery_item_layout
            }

            override fun getOnClickLayout(): (List<Any>) -> Unit {
                val dialog =
                    OngoingDeliveryDetailsFragment()
                val args = Bundle()
                return { params ->
                    args.putString("code", deliveryManCode)
                    args.putInt("deliveryId", params[1].toString().toInt())
                    dialog.arguments = args
                    dialog.show(requireActivity().supportFragmentManager, "OngoingDeliveryDetailsDialog")
                }
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