package com.cccm.crowingrooster.screens.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.cccm.crowingrooster.MainActivity
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class LogInFragment : Fragment() {
    private lateinit var userEditT: EditText
    private lateinit var passEt: EditText
    private lateinit var viewModel: LogInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = DataBindingUtil.inflate<FragmentLogInBinding>(inflater,
            R.layout.fragment_log_in,
            container,false)
        viewModel= ViewModelProvider(this).get(LogInViewModel::class.java)

        bind.apply {
            userEditT = userEt
            passEt = passwordEt

            loginBtt.setOnClickListener {
                if (userEditT.text.toString().isEmpty() || passEt.text.toString().isEmpty()) {
                    Toast.makeText(context, "Porfavor llenar Ambos campos.", Toast.LENGTH_SHORT).show()

                }
                else {
                    ProcessSignIn()

                    val useruid= FirebaseAuth.getInstance().uid
                    val userType= FirebaseDatabase.getInstance().getReference("/users/$useruid")


                    if (userEt.text.toString().toUpperCase() == "SELLER@EXAMPLE.COM") {
                        it.findNavController()
                            .navigate(R.id.action_logInFragment_to_sellerMainScreen)
                    }
                    if (userEt.text.toString().toUpperCase() == "BUYER@EXAMPLE.COM") {
                        it.findNavController()
                            .navigate(R.id.action_logInFragment_to_buyerMainScreenFragment)
                    }
                    if (userEt.text.toString().toUpperCase() == "DEALER@EXAMPLE.COM") {
                        it.findNavController()
                            .navigate(R.id.action_logInFragment_to_openOrdersFragment)
                    }
                }
            }

            registerTv.setOnClickListener{
                it.findNavController().navigate(R.id.action_logInFragment_to_registerFragment)

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

        //Log.d("MainActivity","User: "+email)
        //Log.d("MainActivity","Password: "+pswd)

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pswd)
            .addOnCompleteListener{
                //Log.d("MainActivity", "User Auth Process Correct")




                if(!it.isSuccessful)    return@addOnCompleteListener
            }
            .addOnFailureListener{
                //Log.d("MainActivity", "User Credentials incorrect")
                Toast.makeText(context, "Credenciales Incorrectas. Intente de Nuevo", Toast.LENGTH_SHORT).show()
                return@addOnFailureListener
            }
    }

}
