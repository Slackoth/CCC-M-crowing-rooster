package com.cccm.crowingrooster.screens.product

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.BatteryDao
import com.cccm.crowingrooster.database.daos.SellerDao

import com.cccm.crowingrooster.databinding.FragmentProductBinding
import com.cccm.crowingrooster.network.repository.product.BatteryRepository
import com.cccm.crowingrooster.network.repository.seller.SellerRepository
import com.cccm.crowingrooster.screens.chat.ChatFragment
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
    private lateinit var viewModelFactory: ProductViewModelFactory
    private lateinit var viewModel: ProductViewModel
    private lateinit var batteryDao: BatteryDao

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


        app = requireActivity().application
        batteryDao = CrowingRoosterDataBase.getInstance(app).batteryDao
        batteryRepository = BatteryRepository.getInstance(batteryDao)
        viewModelFactory = ProductViewModelFactory(batteryRepository,app)
        viewModel = ViewModelProvider(this,viewModelFactory).get(ProductViewModel::class.java)


        viewModel.battery.observe(viewLifecycleOwner, Observer {
            binding.ProductTitleText.text=it.modelo
            binding.descriptionProduct.text= "Las Baterias ${it.modelo} poseen dimensiones estandares de ${it.dimensiones} con una polaridad ${it.direccion}  una capacidad de reserva ${it.capacidad_reserva} y un amperaje de ${it.amperaje} siento esta una opcion de calidad ${it.tipo}"
//           context?.let { it1 -> Glide.with(it1).load(it.product_img).into(binding.productImg) }

            Glide.with(binding.productImg.context).load(it.product_img).into(binding.productImg)
        })





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
            it.findNavController().navigate(R.id.action_productFragment_to_chartFragment)
        }




        return binding.root
    }


}