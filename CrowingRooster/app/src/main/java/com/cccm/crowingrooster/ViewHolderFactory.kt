package com.cccm.crowingrooster

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import java.net.URL

object ViewHolderFactory {

    //TODO: Every Layout from a RecyclerView must be bind here
    fun bindView(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.sale_item_layout -> SaleViewHolder(view)
            R.layout.product_item_layout->ProductViewHolder(view)
            R.layout.chart_item_layout->ChartViewHolder(view)
            else -> SaleViewHolder(view)
        }
    }
    //TODO: Every element from a Layout from a RecyclerView must be bind to its respective view here
    class SaleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), GenericRecyclerViewAdapter.Binder<Sale> {

        private val clientEt: EditText = itemView.findViewById(R.id.client_et)
        private val modelEt: EditText = itemView.findViewById(R.id.model_et)
        private val quantityEt: EditText = itemView.findViewById(R.id.quantity_et)
        private val product: ImageView = itemView.findViewById(R.id.img)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.sale_item_layout)

        override fun bind(listObject: Sale, onClickLayout: () -> Unit, context: Context) {
            clientEt.setText(listObject.client)
            modelEt.setText(listObject.model)
            quantityEt.setText(listObject.quantity.toString())
            Glide.with(context).load(listObject.imgUrl).into(product)

            clientEt.inputType = InputType.TYPE_NULL
            modelEt.inputType = InputType.TYPE_NULL
            quantityEt.inputType = InputType.TYPE_NULL

            layout.setOnClickListener {
                onClickLayout()
            }
        }
    }

    class ProductViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), GenericRecyclerViewAdapter.Binder<Product>{



        private val product: ImageView = itemView.findViewById(R.id.Product_img_rv)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.product_item_layout)
        private val desc: TextView= itemView.findViewById(R.id.ProductDesc_et)
        private val ProdTitle: TextView  = itemView.findViewById(R.id.productTitle_et)

        override fun bind(listObject: Product, onClickLayout: () -> Unit, context: Context) {
            Glide.with(context).load(listObject.ProductImg).into(product)
            desc.setText(listObject.ProductDesc)
            ProdTitle.setText(listObject.PrductTitle)

            desc.inputType=InputType.TYPE_NULL
            ProdTitle.inputType=InputType.TYPE_NULL

            layout.setOnClickListener {
                onClickLayout()
            }

        }
    }

    class ChartViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), GenericRecyclerViewAdapter.Binder<ProductChart>{


        private val product: ImageView = itemView.findViewById(R.id.Product_img_ch)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.chart_item_layout)
        private val desc: TextView= itemView.findViewById(R.id.ProductDesc_chr)
        private val ProdTitle: TextView  = itemView.findViewById(R.id.productTitle_ch)
        private val quantity:EditText= itemView.findViewById(R.id.quantity_text_ch)

        override fun bind(listObject: ProductChart, onClickLayout: () -> Unit, context: Context) {
            Glide.with(context).load(listObject.imgUrlCh).into(product)
            desc.text = listObject.ProductDesc
            ProdTitle.text = listObject.PrductTitle
            quantity.setText(listObject.quantity.toString())

            quantity.inputType=InputType.TYPE_NULL





            layout.setOnClickListener {
                onClickLayout()
            }

        }

    }
}
