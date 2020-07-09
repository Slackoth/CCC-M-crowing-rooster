package com.cccm.crowingrooster.screens.sales.ongoing_sales.ongoing_sale_details

import android.app.Application
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cccm.crowingrooster.*
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.SaleDetailsDao
import com.cccm.crowingrooster.database.daos.SaleMiniOrdersDao
import com.cccm.crowingrooster.database.entities.SaleMiniOrders
import com.cccm.crowingrooster.databinding.FragmentOngoingSalesDetailsBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.repository.seller.SaleDetailsRepository
import com.cccm.crowingrooster.screens.sales.ongoing_sales.confirm_sale.ConfirmSaleFragment
import com.cccm.crowingrooster.screens.sales.ongoing_sales.confirm_sale.ConfirmSaleFragmentDirections
import com.cccm.crowingrooster.screens.sales.successful_sales.successful_sale_details.SaleDetailsDialogViewModel
import com.cccm.crowingrooster.screens.sales.successful_sales.successful_sale_details.SaleDetailsDialogViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class OngoingSalesDetailsFragment : Fragment() {

    private lateinit var bind: FragmentOngoingSalesDetailsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: OngoingSalesDetailsViewModel
    private lateinit var viewModelFactory: OngoingSaleDetailsViewModelFactory
    private lateinit var app: Application
    private lateinit var saleDetailsDao: SaleDetailsDao
    private lateinit var saleMiniOrdersDao: SaleMiniOrdersDao
    private lateinit var saleDetailsRepository: SaleDetailsRepository
    private lateinit var adapter: GenericRecyclerViewAdapter<SaleMiniOrders>
    private var args: OngoingSalesDetailsFragmentArgs? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_ongoing_sales_details, container, false)

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.details).capitalize()
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.seller_drawer_menu_navigation)
        }

        args = arguments?.let { OngoingSalesDetailsFragmentArgs.fromBundle(it) }

        app = requireActivity().application
        saleDetailsDao = CrowingRoosterDataBase.getInstance(app).saleDetailsDao
        saleMiniOrdersDao = CrowingRoosterDataBase.getInstance(app).saleMiniOrdersDao
        saleDetailsRepository = SaleDetailsRepository.getInstance(saleDetailsDao,saleMiniOrdersDao)

        viewModelFactory = OngoingSaleDetailsViewModelFactory(saleDetailsRepository,app,args?.code ?: "",args?.orderId ?: "",args?.saleId ?: "")
        viewModel = ViewModelProvider(this,viewModelFactory).get(OngoingSalesDetailsViewModel::class.java)

        bind.apply {
            clientEt.inputType = InputType.TYPE_NULL
            emailEt.inputType = InputType.TYPE_NULL
            dateEt.inputType = InputType.TYPE_NULL
            recyclerView = ongoingSaleDetailsRv

            //TODO: ClickListeners for the buttons
            confirmBtt.setOnClickListener {
                val dialog = ConfirmSaleFragment()
                val dialogArgs = Bundle()
                dialogArgs.putString("saleId",args?.saleId)
                dialogArgs.putString("code",args?.code)
                dialog.arguments = dialogArgs
                dialog.show(requireActivity().supportFragmentManager,"ConfirmDialog")
            }
            charBtt.setOnClickListener {
                it.findNavController().navigate(OngoingSalesDetailsFragmentDirections.actionOngoingSalesDetailsFragmentToChatFragment())
            }
        }

        initRecyclerView()

        viewModel.saleDetails.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                bind.clientEt.setText(it.name)
                bind.emailEt.setText(it.email)
                bind.dateEt.setText(it.date)
                //bind.priceEt.setText(it.price)
                //bind.paymentEt.setText(it.payment)
                bind.totalQuantityTv.text = it.totalQuantity.toString()
            }
            else {
                Log.d("ERROR","NO SIRVE")
            }

        })

        viewModel.miniOrders.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.d("MINI","$it")
                adapter.setDataSource(it)
            }
            else {
                Log.d("ERROR","NO SIRVE")
            }

        })

        return bind.root
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

            override fun getOnClickLayout(): () -> Unit {
                return {}
            }

        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),R.drawable.recyclerview_divider))
        recyclerView.adapter = adapter
    }

}
