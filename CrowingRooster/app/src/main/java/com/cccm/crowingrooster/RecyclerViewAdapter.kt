package com.cccm.crowingrooster

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import de.hdodenhof.circleimageview.CircleImageView

abstract class GenericRecyclerViewAdapter<T>: RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //A list that will contain any type of data, in this case, objects
    private var listItem: MutableList<T>
    private val cContext: Context

    constructor(listOfObjects: MutableList<T>, context: Context) {
        listItem = listOfObjects
        cContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(LayoutInflater.from(parent.context).inflate(getLayoutId(),
        parent, false), getLayoutId())
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(listItem[position], getOnClickLayout(), cContext)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    protected abstract fun getLayoutId(): Int

    abstract fun getOnClickLayout(): () -> Unit

    internal interface Binder<T> {
        fun bind(listObject: T, func: () -> Unit, context: Context)
    }
}