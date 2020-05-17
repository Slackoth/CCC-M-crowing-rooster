package com.cccm.crowingrooster

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import de.hdodenhof.circleimageview.CircleImageView

class RecyclerViewAdapter(context: Context, clientName: MutableList<String>,
img: MutableList<String>, modelName: MutableList<String>, quantityNum: MutableList<Int>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    companion object {
        const val TAG: String = "RecyclerViewAdapter"
    }

    private var cClientName = clientName
    private var cImg = img
    private var cModelName = modelName
    private var cQuantityNum = quantityNum
    private var cContext = context

    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /*Hold the Widgets in memory, each individual entry, it will hold each
                one individually in memory*/
        val img: ImageView = itemView.findViewById(R.id.img)
        val client: EditText = itemView.findViewById(R.id.client_et)
        val model: EditText = itemView.findViewById(R.id.model_et)
        val quantity: EditText = itemView.findViewById(R.id.quantity_et)
        var parentLayout: ConstraintLayout = itemView.findViewById(R.id.parent_layout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item,
        parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG,"onBindViewHolder: called")

        holder.client.inputType = InputType.TYPE_NULL
        holder.model.inputType = InputType.TYPE_NULL
        holder.quantity.inputType = InputType.TYPE_NULL

        Glide.with(cContext).asBitmap().load(cImg[position]).into(holder.img)
        holder.client.setText(cClientName[position])
        holder.model.setText(cModelName[position])
        holder.quantity.setText(cQuantityNum[position].toString())
//        holder.imgName.text = mImageName[position]
        holder.parentLayout.setOnClickListener {
            Log.d(TAG,"onClick: clicked out: ${cClientName[position]}")
            Toast.makeText(cContext,cClientName[position],Toast.LENGTH_SHORT).show()
            //Retrieving the view from a context
            val rootView: View = (cContext as Activity).window.decorView.findViewById(android.R.id.content)
//            Snackbar.make(rootView.findViewById<View>(R.id.successfulSalesFragment), cClientName[position],
//                Snackbar.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return cClientName.size
    }


}