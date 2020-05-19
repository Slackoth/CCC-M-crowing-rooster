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
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import de.hdodenhof.circleimageview.CircleImageView

abstract class GenericRecyclerViewAdapter<T>: RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //A list that will contain any type of data, in this case, objects
    private var listItem: MutableList<T>
    private val cContext: Context
    //private val cActivity: FragmentActivity

    constructor(listOfObjects: MutableList<T>, context: Context/*, activity: FragmentActivity*/) {
        listItem = listOfObjects
        cContext = context
        //cActivity = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sale_item_layout,
        parent, false), viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(listItem[position], cContext, getFragmentActivity())
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getFragmentActivity(): FragmentActivity

    internal interface Binder<T> {
        fun bind(listObject: T, context: Context, fragmentActivity: FragmentActivity)
    }
}