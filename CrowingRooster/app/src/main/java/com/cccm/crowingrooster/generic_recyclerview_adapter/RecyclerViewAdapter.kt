package com.cccm.crowingrooster.generic_recyclerview_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class GenericRecyclerViewAdapter<T>: RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //A list that will contain any type of data, in this case, objects
    private var listItem: MutableList<T>?
    private val cContext: Context

    constructor(listOfObjects: MutableList<T>?, context: Context) {
        listItem = listOfObjects
        cContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(LayoutInflater.from(parent.context).inflate(getLayoutId(),
        parent, false), getLayoutId())
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        listItem?.get(position)?.let { (holder as Binder<T>).bind(it, getOnClickLayout(), cContext) }

    }

    override fun getItemCount(): Int {
        return listItem?.size ?: 0
    }

    fun setDataSource(newList: MutableList<T>?) {
        listItem = newList
        notifyDataSetChanged()
    }

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    protected abstract fun getLayoutId(): Int

    abstract fun getOnClickLayout(): () -> Unit

    internal interface Binder<T> {
        fun bind(listObject: T, func: () -> Unit, context: Context)
    }
}