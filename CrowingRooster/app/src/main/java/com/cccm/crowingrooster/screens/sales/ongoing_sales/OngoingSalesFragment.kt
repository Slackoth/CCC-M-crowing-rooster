package com.cccm.crowingrooster.screens.sales.ongoing_sales

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cccm.crowingrooster.*
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.SalePreviewDao
import com.cccm.crowingrooster.database.entities.SalePreview
import com.cccm.crowingrooster.databinding.FragmentOngoingSalesBinding
import com.cccm.crowingrooster.databinding.FragmentSellerClientListBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.repository.seller.SalePreviewRepository
import com.cccm.crowingrooster.screens.seller_client_list.SellerClientListViewModel
import com.cccm.crowingrooster.screens.seller_client_list.SellerClientViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class OngoingSalesFragment : Fragment() {

    private lateinit var bind: FragmentOngoingSalesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModelFactory: OngoingSalesViewModelFactory
    private lateinit var viewModel: OngoingSalesViewModel
    private lateinit var app: Application
    private lateinit var salePreviewDao: SalePreviewDao
    private lateinit var salePreviewRepository: SalePreviewRepository
    private lateinit var adapter: GenericRecyclerViewAdapter<SalePreview>
    private lateinit var refreshLayout: SwipeRefreshLayout
    private var sellerCode: String? = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(
            inflater, R.layout.fragment_ongoing_sales,
            container, false
        )

        if (arguments != null) {
            sellerCode = arguments?.getString("code")
        }

        Log.d("ongoingsales","$sellerCode")

//        (activity as MainActivity).run {
//            showTopBar()
//            supportActionBar?.title = getString(R.string.ongoing_sales)
//            navigation_view.menu.clear()
//            navigation_view.inflateMenu(R.menu.seller_drawer_menu_navigation)
//        }

        bind.apply {
            recyclerView = salePreviewRv
            lifecycleOwner = this@OngoingSalesFragment
            refreshLayout = ongoingSaleDetailsSrl
        }

        app = requireActivity().application
        salePreviewDao = CrowingRoosterDataBase.getInstance(app).salePreviewDao
        salePreviewRepository = SalePreviewRepository.getInstance(salePreviewDao)

        viewModelFactory = OngoingSalesViewModelFactory(salePreviewRepository,app,sellerCode)
        viewModel = ViewModelProvider(this,viewModelFactory).get(OngoingSalesViewModel::class.java)

        initRecyclerview()

        viewModel.salePreviews.observe(viewLifecycleOwner, Observer {
            adapter.setDataSource(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            when(it) {
                true -> bind.salePreviewListProgressBar.visibility = View.VISIBLE
                else -> bind.salePreviewListProgressBar.visibility = View.GONE
            }
        })

        refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }


        return bind.root
    }

    private fun initRecyclerview() {
        //Creating the RecyclerView Adapter
        adapter = object : GenericRecyclerViewAdapter<SalePreview>(viewModel.salePreviews.value, requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(
                    view,
                    viewType
                )
            }

            override fun getOnClickLayout(): (params: List<Any>) -> Unit {
                return { params ->
                    val globalAction = NavGraphDirections.
                    actionGlobalOngoingSalesFragmentToOngoingSalesDetailsFragment()

                    globalAction.sellerCode = sellerCode.toString()
                    globalAction.orderId = params[0].toString()
                    globalAction.saleId = params[1].toString()

                    try {
                        this@OngoingSalesFragment.findNavController().navigate(globalAction)
                    }
                    catch (e: Exception) {
                        Log.d("Emensaje","${e.message}")
                    }

                }
            }

            override fun getLayoutId(): Int {
                return R.layout.sale_item_layout
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