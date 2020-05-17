package com.cccm.crowingrooster

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.cccm.crowingrooster.databinding.FragmentProductBinding
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




        return binding.root
    }


}