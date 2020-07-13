package com.cccm.crowingrooster.screens.product

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.BatteryDao
import com.cccm.crowingrooster.database.daos.BatteryInfoDao
import com.cccm.crowingrooster.database.daos.PedidoDao
import com.cccm.crowingrooster.database.daos.SellerDao

import com.cccm.crowingrooster.databinding.FragmentProductBinding
import com.cccm.crowingrooster.network.repository.pedido.PedidoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryInfoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryRepository
import com.cccm.crowingrooster.network.repository.seller.SellerRepository
import com.cccm.crowingrooster.screens.chat.ChatFragment
import com.cccm.crowingrooster.screens.sales.ongoing_sales.ongoing_sale_details.OngoingSalesDetailsFragmentArgs
import com.cccm.crowingrooster.screens.search_battery.CatalogueFragment
import com.cccm.crowingrooster.screens.seller_profile.SellerProfileViewModel
import com.cccm.crowingrooster.screens.seller_profile.SellerProfileViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dialog_fullscreen_style_product.view.*


/**
 * A simple [Fragment] subclass.
 */
class ProductFragment : Fragment() {

    private lateinit var app: Application
    private lateinit var batteryRepository: BatteryRepository
//    private lateinit var batteryInfoRepository: BatteryInfoRepository
    private lateinit var PedidoRepository:PedidoRepository
    private lateinit var viewModelFactory: ProductViewModelFactory
    private lateinit var viewModel: ProductViewModel
    private lateinit var batteryDao: BatteryDao
    private lateinit var pedidoDao: PedidoDao
    private var args: ProductFragmentArgs? = null
    lateinit var spinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentProductBinding>(
            inflater,
            R.layout.fragment_product, container, false
        )

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.Producto)
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.buyer_drawer_menu_navigation)
        }

        args = arguments?.let { ProductFragmentArgs.fromBundle(it) }


        app = requireActivity().application
        batteryDao = CrowingRoosterDataBase.getInstance(app).batteryDao
        batteryRepository = BatteryRepository.getInstance(batteryDao)
        pedidoDao= CrowingRoosterDataBase.getInstance(app).PedidoDao
//        batteryInfoRepository= BatteryInfoRepository(batteryInfoDao)
        PedidoRepository= PedidoRepository(pedidoDao)
        viewModelFactory = ProductViewModelFactory(args?.idBattery, batteryRepository,PedidoRepository, app)
        viewModel = ViewModelProvider(this,viewModelFactory).get(ProductViewModel::class.java)

        var uid="20"
        var Buid:Int=0
        var cant:Int=0
        var Desc:String=""
        var img:String=""
        var titulo:String=""
            viewModel.battery.observe(viewLifecycleOwner, Observer {
                if (it!=null) {
                    Desc=    "Las Baterias ${it.modelo} poseen dimensiones estandares de ${it.dimensiones} con una polaridad ${it.direccion}  una capacidad de reserva ${it.capacidad_reserva} y un amperaje de ${it.amperaje} siento esta una opcion de calidad ${it.tipo}"
                    binding.descriptionProduct.text =Desc

                    binding.ProductTitleText.text = it.modelo
                    Glide.with(binding.productImg.context).load(it.product_img)
                        .into(binding.productImg)
                    Buid= it.id_bateria
                    cant= 1
                    img= it.product_img
                    titulo=it.modelo
                }else{
                    Log.d("su putatatamjdasjdm", "me")
                }
            })



        var Estilo:String
        var ArrayEstilos:ArrayList<String>

//        viewModel.batteryInfo.observe(viewLifecycleOwner, Observer {
//            if(it!=null){
//            Estilo="Estilo:  ${it.direccion} ; ${it.tipo}"
//            ArrayEstilos.add(Estilo)
//
//            }else{
//                Log.d("Product", "SeJodio")
//            }
//
//        })


        //        binding.Estilos.setOnClickListener {
    //            val mydialog= LayoutInflater.from(activity).inflate(R.layout.fragment_dialog_fullscreen_style_product,null)
    //
    //            val mBuilder= AlertDialog.Builder(activity)
    //                .setView(mydialog)
    //                .setTitle("Estilos: ")
    //
    //            val mAlertDialog= mBuilder.show()
    //
    //            mydialog.buscar_btn.setOnClickListener {
    //                mAlertDialog.dismiss()
    //            }
    //
    //        }

        binding.AddTochartButtom.setOnClickListener{
            viewModel.SetIntoChart(cant,uid, Buid,pedidoDao, img, Desc, titulo)
            it.findNavController().navigate(R.id.action_productFragment_to_chartFragment)
        }
//        binding.apply {
//            spinner= Estilos
//        }
//        ArrayAdapter.createFromResource(requireContext(),
//            R.array.ArrayEstilos,
//            android.R.layout.simple_spinner_item).also {
//            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner.adapter = it
//        }



        return binding.root
    }


}