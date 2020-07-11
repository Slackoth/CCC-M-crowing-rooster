package com.cccm.crowingrooster.screens.login

import android.app.Application
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
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.UserDao
import com.cccm.crowingrooster.databinding.FragmentLogInBinding
import com.cccm.crowingrooster.network.repository.login.LogInRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class LogInFragment : Fragment() {
    private lateinit var userEditT: EditText
    private lateinit var passEt: EditText
    private lateinit var viewModel: LogInViewModel
    private lateinit var viewModelFactory: LogInViewModelFactory
    private lateinit var userDao: UserDao
    private lateinit var app: Application
    private lateinit var logInRepository: LogInRepository
    private lateinit var type: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = DataBindingUtil.inflate<FragmentLogInBinding>(inflater,
            R.layout.fragment_log_in,
            container,false)

        app = requireActivity().application
        userDao = CrowingRoosterDataBase.getInstance(app).userDao
        logInRepository = LogInRepository.getInstance(userDao)

        viewModelFactory = LogInViewModelFactory(logInRepository,app)
        viewModel = ViewModelProvider(this,viewModelFactory).get(LogInViewModel::class.java)

        viewModel.setUser()

        bind.apply {
            userEditT = userEt
            passEt = passwordEt

            registerTv.setOnClickListener{
                it.findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
            }
            (activity as MainActivity).run {
                hideTopBar()
            }

            bind.loginBtt.setOnClickListener {
                if (userEditT.text.toString().isEmpty() || passEt.text.toString().isEmpty()) {
                    Toast.makeText(context, "Porfavor llenar Ambos campos.", Toast.LENGTH_SHORT).show()

            }
            else {
                ProcessSignIn()
                type = ""
                val user = viewModel.getSpecific(userEditT.text.toString())
                type = if(user != null) {
                    user.type
                } else {
                    "Repartidor"
                }
                val userUid= FirebaseAuth.getInstance().uid
                FirebaseDatabase.getInstance().getReference("/users/$userUid")

                when(type) {
                    "Comprador" ->  {
                        val action = LogInFragmentDirections
                            .actionLogInFragmentToBuyerMainScreenFragment()
                        action.buyerCode = user.code
                        it.findNavController()
                            .navigate(action)
                        //.navigate(R.id.action_logInFragment_to_buyerMainScreenFragment)
                    }
                    "Vendedor" ->  {
                        val action = LogInFragmentDirections.
                        actionLogInFragmentToSellerMainScreen()
                        action.sellerCode = user.code
                        it.findNavController()
                            .navigate(action)
                        //.navigate(R.id.action_logInFragment_to_sellerMainScreen)
                    }
                    else -> it.findNavController()
                        .navigate(R.id.action_logInFragment_to_openOrdersFragment)
                    }
                }
            }
        }

        return bind.root
    }

    private fun ProcessSignIn(){
        val email= userEditT.text.toString()
        val pswd= passEt.text.toString()

        Log.d("MainActivity","User: "+email)
        Log.d("MainActivity","Password: "+pswd)

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pswd)
            .addOnCompleteListener{
                Log.d("MainActivity", "User Auth Process Correct")

                if(!it.isSuccessful) return@addOnCompleteListener
            }
            .addOnFailureListener{
                Log.d("MainActivity", "User Credentials incorrect")
                Toast.makeText(context, "Credenciales Incorrectas. Intente de Nuevo", Toast.LENGTH_SHORT).show()
                return@addOnFailureListener
            }
    }

}