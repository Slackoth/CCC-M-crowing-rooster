package com.cccm.crowingrooster

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

object ViewHolderFactory {

    //TODO: Every Layout from a RecyclerView must be bind here
    fun bindView(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.sale_item_layout -> SaleViewHolder(view)
            else -> SaleViewHolder(view)
        }
    }
    //TODO: Every element from a Layout from a RecyclerView must be bind to its respective view here
    class SaleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), GenericRecyclerViewAdapter.Binder<Sale> {

        private val clientEt: EditText = itemView.findViewById(R.id.client_et)
        private val modelEt: EditText = itemView.findViewById(R.id.model_et)
        private val quantityEt: EditText = itemView.findViewById(R.id.quantity_et)
        private val product: ImageView = itemView.findViewById(R.id.img)

        override fun bind(sale: Sale, context: Context) {
            clientEt.setText(sale.client)
            modelEt.setText(sale.model)
            quantityEt.setText(sale.quantity.toString())
            Glide.with(context).load(sale.imgUrl).into(product)
        }
    }

}