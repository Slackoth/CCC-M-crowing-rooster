package com.cccm.crowingrooster.screens.Register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater,
            R.layout.fragment_register,
            container,false)

        return bind.root
    }
}