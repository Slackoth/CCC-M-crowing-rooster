package com.cccm.crowingrooster.screens.ascending_descending_search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentAscDescDialogBinding

/**
 * A simple [Fragment] subclass.
 */
class AscDescDialogFragment : DialogFragment() {
    private lateinit var bind: FragmentAscDescDialogBinding
    private lateinit var ascRadBtt: RadioButton
    private lateinit var descRadBtt: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_asc_desc_dialog, container, false)
        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_asc_desc_dialog,container,false)

        bind.apply {
            ascRadBtt = ascRb
            descRadBtt = descRb
        }
        ascRadBtt.setOnClickListener {
            dialog?.dismiss()
        }
        descRadBtt.setOnClickListener {
            dialog?.dismiss()
        }


        return bind.root
    }

}
