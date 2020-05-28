package com.cccm.crowingrooster

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.cccm.crowingrooster.databinding.FragmentProductBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dialog_fullscreen_style_product.view.*


/**
 * A simple [Fragment] subclass.
 */
class ProductFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentProductBinding>(
            inflater, R.layout.fragment_product, container, false
        )

        (activity as MainActivity).run {
            showTopBar()
            supportActionBar?.title = getString(R.string.catalogo)
            navigation_view.menu.clear()
            navigation_view.inflateMenu(R.menu.buyer_drawer_menu_navigation)
        }



        binding.Estilos.setOnClickListener {
            val mydialog= LayoutInflater.from(activity).inflate(R.layout.fragment_dialog_fullscreen_style_product,null)

            val mBuilder= AlertDialog.Builder(activity)
                .setView(mydialog)
                .setTitle("Filtros")

            val mAlertDialog= mBuilder.show()

            mydialog.buscar_btn.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }

        binding.AddTochartButtom.setOnClickListener{
            it.findNavController().navigate(R.id.action_productFragment_to_chartFragment)
        }




        return binding.root
    }


}