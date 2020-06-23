package com.cccm.crowingrooster.screens.Register

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentRegisterBinding
import com.cccm.crowingrooster.screens.sales.successful_sales.TAG
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*

class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    var selectedPhotoUri: Uri? = null
    private val IMAGE_PICK_CODE = 1000;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater,
            R.layout.fragment_register,
            container,false)


        bind.apply {


            //add image
            selectphotoButtonRegister.setOnClickListener{
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                //startActivityForResult(intent, IMAGE_PICK_CODE)
               // performProfileUpload()
            }

            registerButtonRegister.setOnClickListener{

            }
        }

        return bind.root
    }
/*
    private fun performProfileUpload(){
        if (selectedPhotoUri == null) return

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d(TAG, "Successfully uploaded image: ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {
                    Log.d(TAG, "File Location: $it")

                    Log.d("RegisterAct", selectedPhotoUri.toString())
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed to upload image to storage: ${it.message}")
            }
    }*/
}