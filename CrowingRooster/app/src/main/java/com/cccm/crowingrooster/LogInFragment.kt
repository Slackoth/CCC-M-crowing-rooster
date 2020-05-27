package com.cccm.crowingrooster

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.cccm.crowingrooster.databinding.FragmentLogInBinding

/**
 * A simple [Fragment] subclass.
 */
class LogInFragment : Fragment() {
    private lateinit var userEditT: EditText
    private lateinit var passEt: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_log_in, container, false)
        val bind = DataBindingUtil.inflate<FragmentLogInBinding>(inflater,R.layout.fragment_log_in,
        container,false)

        bind.apply {
            userEditT = userEt
            passEt = passwordEt
            loginBtt.setOnClickListener {
                if (userEditT.text.toString() == "seller") {
                    it.findNavController().navigate(R.id.action_logInFragment_to_sellerMainScreen)
                }
                else if(userEditT.text.toString() == "buyer") {
                    it.findNavController().navigate(R.id.action_logInFragment_to_buyerMainScreenFragment)
                }
                else if(userEditT.text.toString() == "dealer") {
                    it.findNavController().navigate(R.id.action_logInFragment_to_openOrdersFragment)
                }
            }
        }

        (activity as MainActivity).run {
            hideTopBar()
        }
        return bind.root
    }


}
