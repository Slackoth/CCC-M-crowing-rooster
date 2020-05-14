package com.cccm.crowingrooster

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class RecyclerViewAdapter(context: Context, imageName: MutableList<String>,
image: MutableList<String>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    companion object {
        const val TAG: String = "RecyclerViewAdapter"
    }

    private var mImageName = imageName
    private var mImage = image
    private var mContext = context

    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /*Hold the Widgets in memory, each individual entry, it will hold each
                one individually in memory*/
        var img: CircleImageView = itemView.findViewById(R.id.img)
        var imgName: TextView = itemView.findViewById(R.id.img_name)
        var parentLayout: RelativeLayout = itemView.findViewById(R.id.parent_layout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item,
        parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG,"onBindViewHolder: called")

        Glide.with(mContext).asBitmap().load(mImage[position]).into(holder.img)
        holder.imgName.text = mImageName[position]
        holder.parentLayout.setOnClickListener {
            Log.d(TAG,"onClick: clicked out: ${mImageName[position]}")
            Toast.makeText(mContext,mImageName[position],Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return mImageName.size
    }


}