package com.cccm.crowingrooster.screens.Register

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.createBitmap
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.cccm.crowingrooster.R
import com.cccm.crowingrooster.databinding.FragmentRegisterBinding
import com.cccm.crowingrooster.screens.sales.successful_sales.TAG
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*
import android.content.ContentProvider as ContentProvider

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

            selectphoto_imageview_register.setBackgroundDrawable(drawable)
            selectphoto_button_register.alpha = 0f

        //selectphoto_button_register.setBackgroundDrawable(//bitmapDrawable)
        }

        Toast.makeText(context, "F no sale", Toast.LENGTH_LONG).show()
    }
}