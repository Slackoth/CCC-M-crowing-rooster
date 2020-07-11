package com.cccm.crowingrooster.screens.register

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.cccm.crowingrooster.NavGraphDirections
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.database.CrowingRoosterDataBase
import com.cccm.crowingrooster.database.daos.order.BuyerDao
import com.cccm.crowingrooster.database.entities.order.Buyer
import com.cccm.crowingrooster.databinding.FragmentRegisterBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.UserDatabase
import com.cccm.crowingrooster.network.body.RegisterBuyer
import com.cccm.crowingrooster.network.repository.order.RegisterBuyerRepository
import com.cccm.crowingrooster.screens.sales.successful_sales.TAG

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*

class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    var uri: Uri? = null
    private lateinit var bind: FragmentRegisterBinding
    private lateinit var registerBuyerRepository: RegisterBuyerRepository
    private lateinit var viewModel: RegisterViewModel
    private lateinit var viewModelFactory: RegisterViewModelFactory
    private lateinit var app: Application
    private val IMAGE_PICK_CODE = 1000;
    abstract class ContentResolver
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater,
            R.layout.fragment_register,
            container,false)

        app = requireActivity().application
        registerBuyerRepository = RegisterBuyerRepository.getInstance()

        viewModelFactory = RegisterViewModelFactory(registerBuyerRepository,app)
        viewModel = ViewModelProvider(this,viewModelFactory).get(RegisterViewModel::class.java)

        bind.apply {
            //add image
            selectphotoButtonRegister.setOnClickListener{
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 0)
            }

            registerButtonRegister.setOnClickListener{
                performRegister()
            }
        }

        return bind.root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            // get image
            Toast.makeText(context, "Foto guardada", Toast.LENGTH_SHORT).show()

                uri= data.data
                Log.d("registerFrag", uri.toString())

            val inputStream = uri?.let { activity?.contentResolver?.openInputStream(it) }
            val drawable = Drawable.createFromStream(inputStream, uri.toString())

            context?.let { Glide.with(it).load(uri.toString()).into(selectphoto_imageview_register) }
            selectphoto_button_register.alpha = 0f
        }

        //Toast.makeText(context, "No se ha podido realizar la acción", Toast.LENGTH_LONG).show()
    }


    private fun performRegister(){
        val email = email_edittext_register.text.toString().toLowerCase()
        val password = password_edittext_register.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Rellene todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d(TAG, "Attempting to create user with email: $email")

        //recordar el tolowercase para estatus, saludos
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                // else if successful
                Log.d(TAG, "Successfully created user with uid: ${it.result?.user?.uid}")

                uploadimageCloud()
            }
            .addOnFailureListener{
                Log.d(TAG, "Failed to create user: ${it.message}")
                Toast.makeText(context, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun uploadimageCloud(){
        if (uri == null) return

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(uri!!)
            .addOnSuccessListener {
                Log.d(TAG, "Successfully uploaded image: ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {
                    Log.d(TAG, "File Location: $it")

                    saveUserToDatabase(it.toString())
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed to upload image to storage: ${it.message}")
            }
    }


    private fun saveUserToDatabase(profileImageUrl:String){
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        //uio status username profile
        val user = UserDatabase(uid,"buyer",username_edittext_register.text.toString(), profileImageUrl )

        ref.setValue(user)
            .addOnSuccessListener {
                registerInApi(profileImageUrl)
                this@RegisterFragment.findNavController().navigate(NavGraphDirections
                    .actionGlobalRegisterFragmentToLogInFragment())
                Log.d(TAG, "Finally we saved the user to Firebase Database")
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed to set value to database: ${it.message}")
            }
    }

    private fun registerInApi(img: String) {
        val registerBuyer = RegisterBuyer(
            bind.usernameEdittextRegister.text.toString(),
            bind.registerNameEt.text.toString(),
            "Jellybutter",
            bind.registerDuiEt.text.toString(),
            bind.emailEdittextRegister.text.toString(),
            bind.registerPhoneEt.text.toString(),
            img,
            bind.passwordEdittextRegister.text.toString()
        )
        viewModel.register(registerBuyer)
    }
}