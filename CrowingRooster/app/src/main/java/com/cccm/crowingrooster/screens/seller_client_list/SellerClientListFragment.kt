package com.cccm.crowingrooster.screens.seller_client_list

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.*
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.BuyerDao
import com.cccm.crowingrooster.database.entities.Buyer
import com.cccm.crowingrooster.databinding.FragmentSellerClientListBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.Client
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.screens.ascending_descending_search.AscDescDialogFragment
import com.cccm.crowingrooster.screens.sales.successful_sales.successful_sale_details.SaleDetailsDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 */
class SellerClientListFragment : Fragment() {
    private lateinit var bind: FragmentSellerClientListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModelFactory: SellerClientViewModelFactory
    private lateinit var viewModel: SellerClientListViewModel
    private lateinit var app: Application
    private lateinit var dataSource: BuyerDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_seller_client_list, container, false)

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.clients)
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.seller_drawer_menu_navigation)
        }

        setHasOptionsMenu(true)

        app = requireActivity().application
        dataSource = CrowingRoosterDataBase.getInstance(app).buyerDao
        var a = CrowingRoosterDataBase.getInstance(app)

        viewModelFactory = SellerClientViewModelFactory(dataSource,app)
        viewModel = ViewModelProvider(this,viewModelFactory).get(SellerClientListViewModel::class.java)

        Log.d("COMPANY","${a.companyDao.getAll()}")
        Log.d("DSTATE","${a.deliveryStateDao.getAll()}")
        Log.d("PAYMENT","${a.paymentMethodDao.getAll()}")
        Log.d("POLAR","${a.polarityDao.getAll()}")
        Log.d("QUA","${a.qualityDao.getAll()}")

        recyclerView = bind.clientRv
//        bind.button.setOnClickListener {
//            viewModel.insertClient(Buyer("C","C","C",1))
//            viewModel.getClient()
//        }

        initRecyclerview()

        viewModel.clients.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter?.notifyDataSetChanged()
        })

        return bind.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.clients_list_more_options_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.action_order_client -> {
                val dialog = AscDescDialogFragment()
                dialog.show(requireActivity().supportFragmentManager,"ClientOrderDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerview() {

        val adapter = object : GenericRecyclerViewAdapter<Any>(viewModel.clients.value!!, requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(
                    view,
                    viewType
                )
            }

            override fun getLayoutId(): Int {
                return R.layout.client_item_layout
            }

            override fun getOnClickLayout(): () -> Unit {
                val dialog =
                    SaleDetailsDialogFragment()
                return { -> dialog.show(requireActivity().supportFragmentManager, "SaleDetailsDialog") }
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
