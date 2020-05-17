package com.cccm.crowingrooster

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.cccm.crowingrooster.databinding.FragmentProductBinding
import com.cccm.crowingrooster.databinding.FragmentProductBindingImpl
import com.cccm.crowingrooster.databinding.FragmentProductBindingLandImpl

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
            val mydialog= LayoutInflater.from(this).inflate(R.layout.fragment_dialog_fullscreen_style_product,null)

            val mBuilder= AlertDialog.Builder(this)
                .setView(mydialog)
                .setTitle("Estilos de Bateria")

        }


        return binding.root
    }


}
