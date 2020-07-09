package com.cccm.crowingrooster.screens.sales.successful_sales

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cccm.crowingrooster.*
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.SalePreviewDao
import com.cccm.crowingrooster.database.entities.SalePreview
import com.cccm.crowingrooster.databinding.FragmentSuccessfulSalesBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.repository.seller.SalePreviewRepository
import com.cccm.crowingrooster.screens.sales.successful_sales.successful_sale_details.SaleDetailsDialogFragment

/**
 * A simple [Fragment] subclass.
 */

const val TAG = "HighOrder"

class SuccessfulSalesFragment : Fragment() {
    private lateinit var bind: FragmentSuccessfulSalesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: SuccessfulSalesViewModel
    private lateinit var viewModelFactory: SuccessfulSalesViewModelFactory
    private lateinit var app: Application
    private lateinit var salePreviewDao: SalePreviewDao
    private lateinit var salePreviewRepository: SalePreviewRepository
    private lateinit var adapter: GenericRecyclerViewAdapter<SalePreview>
    private lateinit var refreshLayout: SwipeRefreshLayout
    private var sellerCode: String? = ""


    @SuppressLint("LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(
            inflater, R.layout.fragment_successful_sales,
            container, false
        )

        if (arguments != null) {
            sellerCode = arguments?.getString("sellerCode")
        }

        app = requireActivity().application
        salePreviewDao = CrowingRoosterDataBase.getInstance(app).salePreviewDao
        salePreviewRepository = SalePreviewRepository.getInstance(salePreviewDao)

        viewModelFactory =
            SuccessfulSalesViewModelFactory(
                salePreviewRepository,
                app
            )
        viewModel = ViewModelProvider(this,viewModelFactory).get(SuccessfulSalesViewModel::class.java)

        bind.apply {
            recyclerView = succesfulSalesRv
            refreshLayout = succesfulSalesSrl
            lifecycleOwner = this@SuccessfulSalesFragment
        }

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



//        TODO: In case someday we need to change the orientation of the RecyclerView. DO NOT DELETE IT
//        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
//            if (orientation == Configuration.ORIENTATION_LANDSCAPE) LinearLayoutManager.HORIZONTAL
//            else LinearLayoutManager.VERTICAL, false)

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

            override fun getOnClickLayout(): (List<Any>) -> Unit {
                val dialog =
                    SaleDetailsDialogFragment()
                val args = Bundle()
                return { params ->
                    args.putString("sellerCode",sellerCode)
                    args.putString("orderId",params[0].toString())
                    args.putString("saleId",params[1].toString())
                    dialog.arguments = args
                    dialog.show(requireActivity().supportFragmentManager, "SaleDetailsDialog")
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
