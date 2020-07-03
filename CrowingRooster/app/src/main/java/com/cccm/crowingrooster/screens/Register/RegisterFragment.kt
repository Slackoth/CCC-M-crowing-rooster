package com.cccm.crowingrooster.screens.Register

import android.app.Activity
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
import com.bumptech.glide.Glide
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentRegisterBinding
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.UserDatabase
import com.cccm.crowingrooster.screens.sales.successful_sales.TAG

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*

class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    var uri: Uri? = null
    private val IMAGE_PICK_CODE = 1000;
    abstract class ContentResolver
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
                startActivityForResult(intent, 0)
               // performProfileUpload()
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
            Toast.makeText(context, "FotoGuardada", Toast.LENGTH_SHORT).show()


                uri= data.data
                Log.d("registerFrag", uri.toString())

                //val bitmap = createBitmap(500,500,)
            //set image in the space *not rounded*
            val inputStream = uri?.let { activity?.contentResolver?.openInputStream(it) }
            val drawable = Drawable.createFromStream(inputStream, uri.toString())

            //selectphoto_imageview_register.setBackgroundDrawable(drawable)
            context?.let { Glide.with(it).load(uri.toString()).into(selectphoto_imageview_register) }
            selectphoto_button_register.alpha = 0f

        //selectphoto_button_register.setBackgroundDrawable(//bitmapDrawable)
        }

        Toast.makeText(context, "F no sale", Toast.LENGTH_LONG).show()
    }


    private fun performRegister(){
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d(TAG, "Attempting to create user with email: $email")

        // Firebase Authentication to create a user with email and password
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
                Log.d(TAG, "Finally we saved the user to Firebase Database")



            }
            .addOnFailureListener {
                Log.d(TAG, "Failed to set value to database: ${it.message}")
            }
    }
}