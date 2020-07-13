package com.cccm.crowingrooster.screens.chart

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.BatteryDao
import com.cccm.crowingrooster.database.daos.OrdertoChartDao
import com.cccm.crowingrooster.database.daos.PedidoDao
import com.cccm.crowingrooster.database.daos.SellerFreeDao
import com.cccm.crowingrooster.database.entities.Pedido
import com.cccm.crowingrooster.database.entities.SellerFree
import com.cccm.crowingrooster.databinding.FragmentChartBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.ProductChart
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.repository.pedido.OrderToChartRepository
import com.cccm.crowingrooster.network.repository.pedido.PedidoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryRepository
import com.cccm.crowingrooster.network.repository.seller.SellerFreeRepository
import com.cccm.crowingrooster.screens.product.ProductFragmentArgs
import com.cccm.crowingrooster.screens.product.ProductViewModel
import com.cccm.crowingrooster.screens.product.ProductViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class ChartFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var chartList: MutableList<Any> = mutableListOf()
    private lateinit var app: Application
    private lateinit var orderToChartRepository: OrderToChartRepository
    private lateinit var SellerFreeRepository:SellerFreeRepository
    private lateinit var PedidoRepository:PedidoRepository
    private lateinit var viewModelFactory: ChartViewModelFactory
    private lateinit var viewModel: ChartViewModel
    private lateinit var SellerFreeDao:SellerFreeDao
    private lateinit var pedidoDao: PedidoDao
    private lateinit var adapter: GenericRecyclerViewAdapter<Pedido>
    private lateinit var ordertoChartDao: OrdertoChartDao
    lateinit var ListaPedidos :List<Pedido>
    private var args: ChartFragmentArgs? = null
     var email:String=""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bind = DataBindingUtil.inflate<FragmentChartBinding>(inflater,
            R.layout.fragment_chart,
            container,false)

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.chart)
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.buyer_drawer_menu_navigation)
        }


        args = arguments?.let { ChartFragmentArgs.fromBundle(it) }



        app = requireActivity().application
        pedidoDao = CrowingRoosterDataBase.getInstance(app).PedidoDao
        SellerFreeDao= CrowingRoosterDataBase.getInstance(app).sellerFreeDao
        ordertoChartDao=CrowingRoosterDataBase.getInstance(app).OrdertoChartDao
        orderToChartRepository = OrderToChartRepository(ordertoChartDao)

        PedidoRepository= PedidoRepository(pedidoDao)
        SellerFreeRepository= SellerFreeRepository(SellerFreeDao)
        viewModelFactory = ChartViewModelFactory(PedidoRepository,SellerFreeRepository, orderToChartRepository, app)
        viewModel = ViewModelProvider(this,viewModelFactory).get(ChartViewModel::class.java)



       viewModel.pedidos.observe(viewLifecycleOwner, Observer {
           if(it!= null){
               adapter.notifyDataSetChanged()
               ListaPedidos=it
               adapter.notifyDataSetChanged()
               adapter.setDataSource(it)
           }
       })



        viewModel.freeSeller.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                //Log.d("SellerId", it.email +"quiza no tira nada")
                viewModel.getSellerUId(it.email)
            }
        })



        bind.ProceedPay.setOnClickListener {
            var UserCode= args!!.buyerCode
            viewModel.doOrderInsert(OrdertoChartDao = ordertoChartDao, listaPedidos = ListaPedidos, code = GetOrderCode(),
                codeUser = UserCode
            )
            //Log.d("Proceed", "Äl menos llega" )
            viewModel.CreateChat()
            it.findNavController().navigate(R.id.action_chartFragment_to_chatFragment)
        }


        recyclerView = bind.recyclerViewChart







        //Creating the RecyclerView Adapter

        adapter= object : GenericRecyclerViewAdapter<Pedido>(viewModel.pedidos.value, requireContext()) {

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(view, viewType)
            }

            override fun getLayoutId(): Int {
                return R.layout.chart_item_layout
            }

            override fun getOnClickLayout(): (List<Any>) -> Unit {
                return {
                    this@ChartFragment.findNavController()
                        .navigate(R.id.action_chartFragment_to_productFragment)
                }
            }
        }
            adapter.notifyDataSetChanged()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //Adding the divider
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                R.drawable.recyclerview_divider
            )
        )
//        recyclerView.adapter.clear()
        recyclerView.adapter = adapter

        return bind.root
    }

    fun GetOrderCode(): String{
        var code:String=""

        viewModel.codigo.observe(viewLifecycleOwner, Observer {
            if(it!= null){
                Log.d("SellerId", it +"quiza no tira nada *3333")
                code= it
            }
        })








        return code
    }

}

