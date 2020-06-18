package com.cccm.crowingrooster.screens.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth

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
        val bind = DataBindingUtil.inflate<FragmentLogInBinding>(inflater,
            R.layout.fragment_log_in,
        container,false)

        bind.apply {
            userEditT = userEt
            passEt = passwordEt

            loginBtt.setOnClickListener {
                ProcessSignIn()
                if(userEt.text.toString().toUpperCase()=="SELLER@EXAMPLE.COM"){
                    it.findNavController().navigate(R.id.action_logInFragment_to_sellerMainScreen)
                }
                if (userEt.text.toString().toUpperCase()=="BUYER@EXAMPLE.COM"){
                    it.findNavController().navigate(R.id.action_logInFragment_to_buyerMainScreenFragment)
                }
                if (userEt.text.toString().toUpperCase()=="DEALER@EXAMPLE.COM"){
                    it.findNavController().navigate(R.id.action_logInFragment_to_openOrdersFragment)
                }
            }
        (activity as MainActivity).run {
            hideTopBar()
        }
        return bind.root
        }

    }
    private fun ProcessSignIn(){
        val email= userEditT.text.toString()
        val pswd= passEt.text.toString()


        Log.d("MainActivity","User: "+email)
        Log.d("MainActivity","Password: "+pswd)

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pswd)
            .addOnCompleteListener{
                Log.d("MainActivity", "User Auth Correct")
                //SigInActivity(email)
                if(!it.isSuccessful) return@addOnCompleteListener
            }
            .addOnFailureListener{
                Log.d("MainActivity", "User Credentials incorrect")
                //Toast.makeText(this@LogInFragment, "Credenciales Incorrectas. Intente de Nuevo", Toast.LENGTH_SHORT).show()
            }
    }

}
