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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.BatteryDao
import com.cccm.crowingrooster.database.daos.PedidoDao
import com.cccm.crowingrooster.database.entities.Pedido
import com.cccm.crowingrooster.databinding.FragmentChartBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.DividerItemDecoration
import com.cccm.crowingrooster.generic_recyclerview_adapter.GenericRecyclerViewAdapter
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.ProductChart
import com.cccm.crowingrooster.generic_recyclerview_adapter.ViewHolderFactory
import com.cccm.crowingrooster.network.repository.pedido.PedidoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryRepository
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
    private lateinit var batteryRepository: BatteryRepository
    //    private lateinit var batteryInfoRepository: BatteryInfoRepository
    private lateinit var PedidoRepository:PedidoRepository
    private lateinit var viewModelFactory: ChartViewModelFactory
    private lateinit var viewModel: ChartViewModel
    private lateinit var batteryDao: BatteryDao
    private lateinit var pedidoDao: PedidoDao
    private lateinit var adapter: GenericRecyclerViewAdapter<Pedido>




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

        app = requireActivity().application
        pedidoDao = CrowingRoosterDataBase.getInstance(app).PedidoDao

//        batteryInfoRepository= BatteryInfoRepository(batteryInfoDao)
        PedidoRepository= PedidoRepository(pedidoDao)
        viewModelFactory = ChartViewModelFactory(PedidoRepository, app)
        viewModel = ViewModelProvider(this,viewModelFactory).get(ChartViewModel::class.java)

       viewModel.pedidos.observe(viewLifecycleOwner, Observer {
           if(it!= null){
               //Log.d("Chart", it[1].desc_bateria)
               adapter.setDataSource(it)
           }
       })





        recyclerView = bind.recyclerViewChart
//        chartList.addAll(
//            listOf(
//                ProductChart(
//                    "Baterias rasho",
//                    "Spicy jalapeno bacon ipsum dolor amet corned beef leberkas ribeye biltong capicola chicken shoulder meatloaf pork belly. ",
//                    5,
//                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
//                ),
//                ProductChart(
//                    "Baterias Trueno",
//                    "huck swine pancetta kevin, beef pork loin pork chop short ribs chislic pork.huck swine pancetta kevin, beef pork loin pork chop short ribs chislic pork.",
//                    1,
//                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
//                ),
//                ProductChart(
//                    "Baterias Rayito",
//                    "Brisket spare ribs alcatra short loin jowl venison pork loin beef ribs pastrami drumstick chuck",
//                    2,
//                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
//                ),
//                ProductChart(
//                    "Baterias Esclavas del Blanco",
//                    "Beef biltong kevin bacon ribeye t-bone short loin drumstick brisket shankle shank.",
//                    6,
//                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
//                )
//
//            )
//        )

        //Creating the RecyclerView Adapter
        adapter= object : GenericRecyclerViewAdapter<Pedido>(viewModel.pedidos.value, requireContext()) {

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.bindView(view, viewType)
            }

            override fun getLayoutId(): Int {
                return R.layout.chart_item_layout
            }

            override fun getOnClickLayout(): () -> Unit {
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
        recyclerView.adapter = adapter

        return bind.root
    }

}

