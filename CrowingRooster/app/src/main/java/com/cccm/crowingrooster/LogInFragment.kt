package com.cccm.crowingrooster

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.cccm.crowingrooster.databinding.FragmentLogInBinding

/**
 * A simple [Fragment] subclass.
 */
class LogInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_log_in, container, false)
        val bind = DataBindingUtil.inflate<FragmentLogInBinding>(inflater,R.layout.fragment_log_in,
        container,false)

        bind.apply {
            loginButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_logInFragment_to_sellerMainScreen)
            }
        }

        (activity as MainActivity).run {
            hideTopBar()
        }
        return bind.root
    }


}
