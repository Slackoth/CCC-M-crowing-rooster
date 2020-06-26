package com.cccm.crowingrooster.screens.seller_client_list

import android.annotation.SuppressLint
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
import com.cccm.crowingrooster.data.ApixuKantoPokemonApiService
import com.cccm.crowingrooster.data.network.ConnectivityInterceptorImpl
import com.cccm.crowingrooster.data.network.PokemonNetworkDataSource
import com.cccm.crowingrooster.data.network.PokemonNetworkDataSourceImpl
import com.cccm.crowingrooster.data.network.response.KantoPokemon
import com.cccm.crowingrooster.data.repository.KantoPokemonRepository
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.BuyerDao
import com.cccm.crowingrooster.database.daos.UserDao
import com.cccm.crowingrooster.database.entities.Buyer
import com.cccm.crowingrooster.databinding.FragmentSellerClientListBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.Client
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.screens.ScopeFragment
import com.cccm.crowingrooster.screens.ascending_descending_search.AscDescDialogFragment
import com.cccm.crowingrooster.screens.sales.successful_sales.successful_sale_details.SaleDetailsDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * A simple [Fragment] subclass.
 */
class SellerClientListFragment : ScopeFragment(), KodeinAware {
    private lateinit var bind: FragmentSellerClientListBinding
    private lateinit var recyclerView: RecyclerView
    override val kodein by kodein()
    //private lateinit var viewModelFactory: SellerClientViewModelFactory
    //private val viewModelFactory = SellerClientViewModelFactory()
    private val viewModelFactory by instance<SellerClientViewModelFactory>()
    private lateinit var viewModel: SellerClientListViewModel
    /*private lateinit var app: Application
    private lateinit var buyerSource: BuyerDao
    private lateinit var userSource: UserDao*/
    private lateinit var adapter: GenericRecyclerViewAdapter<Buyer>
    //val b: MutableList<Any> = mutableListOf()

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

        /*app = requireActivity().application
        buyerSource = CrowingRoosterDataBase.getInstance(app).buyerDao
        userSource = CrowingRoosterDataBase.getInstance(app).userDao*/

        //var a = CrowingRoosterDataBase.getInstance(app)

        /*viewModelFactory = SellerClientViewModelFactory(buyerSource,userSource,app)
        viewModel = ViewModelProvider(this,viewModelFactory).get(SellerClientListViewModel::class.java)*/

//        Log.d("BUYER","${a.buyerDao.getAll().value}")
//        Log.d("COMPANY","${a.companyDao.getAll()}")
//        Log.d("DSTATE","${a.deliveryStateDao.getAll()}")
//        Log.d("PAYMENT","${a.paymentMethodDao.getAll()}")
//        Log.d("POLAR","${a.polarityDao.getAll()}")
//        Log.d("QUA","${a.qualityDao.getAll()}")

        bind.apply {
            recyclerView = clientRv
            lifecycleOwner = this@SellerClientListFragment
            //sellerClientListViewModel = viewModel
        }
        /*bind.button.setOnClickListener {
            //viewModel.test()
            Log.d("ESPALDA","${viewModel.clients.value}")
            viewModel.uiScope.launch {
                viewModel.addBuyer()
            }
        }*/

        initRecyclerview()

        /*viewModel.clients.observe(viewLifecycleOwner, Observer {
            adapter.setDataSource(it)

        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            when(it) {
                true -> bind.clientListProgressBar.visibility = View.VISIBLE
                else -> bind.clientListProgressBar.visibility = View.GONE
            }
        })*/

        return bind.root
    }

    @SuppressLint("LogNotTimber")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this,viewModelFactory).get(SellerClientListViewModel::class.java)

        bindUI()

//        val apiService =
//            ApixuKantoPokemonApiService(ConnectivityInterceptorImpl(requireContext()))
//        val networkDataSource = PokemonNetworkDataSourceImpl(apiService)
//
//        networkDataSource.downloadedPokemon.observe(viewLifecycleOwner, Observer {
//            Log.d("KantoPokemon","$it")
//        })
//
//        GlobalScope.launch(Dispatchers.Main) {
//                networkDataSource.fetchPokemon()
////            val kantoPokemon = apiService.getAllAsync()
////            Log.d("KantoPokemon","$kantoPokemon")
//        }
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

        adapter = object : GenericRecyclerViewAdapter<Buyer>(mutableListOf(), requireContext()) {
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

    private fun bindUI() = launch {
        val pokemon = viewModel.pokemon.await()
        pokemon.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            Log.d("VERGA","$it")
            bind.clientListProgressBar.visibility = View.GONE
            bind.mierda.text = it.toString()
        })
    }

}
