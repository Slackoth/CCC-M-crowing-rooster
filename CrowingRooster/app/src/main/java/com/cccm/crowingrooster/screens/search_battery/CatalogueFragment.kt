package com.cccm.crowingrooster.screens.search_battery

import android.app.Application
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.NavGraphDirections
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.CatalogueDao
import com.cccm.crowingrooster.database.entities.Catalogue
import com.cccm.crowingrooster.databinding.FragmentBatterySearchBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.repository.catalogue.CatalogueRepository
import com.cccm.crowingrooster.screens.buyer_main_screen.BuyerMainScreenFragmentArgs
import com.cccm.crowingrooster.screens.product.ProductFragmentArgs
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class CatalogueFragment : Fragment() {

    private lateinit var app: Application
    private lateinit var catalogueRepository: CatalogueRepository
    private lateinit var catalogueDao: CatalogueDao
    private lateinit var bind: FragmentBatterySearchBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CatalogueViewModel
    private lateinit var viewModelFactory: CatalogueViewModelFactory
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var adapter: GenericRecyclerViewAdapter<Catalogue>
    private var args: CatalogueFragmentArgs? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_battery_search, container, false)

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = "Catálogo".capitalize()
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.buyer_drawer_menu_navigation)
        }

        setHasOptionsMenu(true)

        app = requireActivity().application
        catalogueDao = CrowingRoosterDataBase.getInstance(app).catalogueDao
        catalogueRepository = CatalogueRepository.getInstance(catalogueDao)
        viewModelFactory = CatalogueViewModelFactory(catalogueRepository, app)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CatalogueViewModel::class.java)

        bind.apply {
            recyclerView = catalogueRv
            lifecycleOwner = this@CatalogueFragment
            catalogueViewModel = viewModel
            refreshLayout = catalogueSrl
        }

        args = arguments?.let {
            CatalogueFragmentArgs.fromBundle(it)
        }
        initRecyclerView()

        refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        viewModel.batteriesOnStock.observe(viewLifecycleOwner, Observer {
            adapter.setDataSource(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            when(it) {
                true -> bind.catalogueProgressBar.visibility = View.VISIBLE
                else -> bind.catalogueProgressBar.visibility = View.GONE
            }
        })
        return bind.root
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu, menu)

        val manager = (activity as MainActivity).getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.action_search)
        //val searchView= searchItem?.actionView as SearchView

        //searchView.setSearchableInfo(manager.getSearchableInfo((activity as MainActivity).componentName))

        /*searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                //searchView.clearFocus()
                //searchView.setQuery( "", false)
                val collapseActionView = searchItem.collapseActionView()

                Toast.makeText(requireContext(), "Looking for $query", Toast.LENGTH_LONG).show()

                return true
            }

          override fun onQueryTextChange(newText: String?): Boolean {
              return false           }

        })*/

        super.onCreateOptionsMenu(menu, inflater)
    }*/

    private fun initRecyclerView() {
        adapter = object : GenericRecyclerViewAdapter<Catalogue>(viewModel.batteriesOnStock.value, requireContext()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(
                    view,
                    viewType
                )
            }

            override fun getLayoutId(): Int {
                return R.layout.searchbt_item_layout
            }

            override fun getOnClickLayout(): (List<Any>) -> Unit {
                return { params ->
                    val globalAction = NavGraphDirections.actionGlobalCatalogueFragmentToProductFragment()
                    globalAction.idBattery = params[0].toString().toInt()
                    globalAction.buyerCode= args!!.buyerCode


                    try {
                        this@CatalogueFragment.findNavController().navigate(globalAction)

                    }catch (e: Exception) {
                        Log.d("Emensaje","${e.message}")}
                }
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