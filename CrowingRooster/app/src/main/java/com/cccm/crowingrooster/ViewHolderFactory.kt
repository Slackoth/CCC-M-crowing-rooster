package com.cccm.crowingrooster

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.text.InputType
import android.view.View
import android.widget.*
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
import java.text.FieldPosition

object ViewHolderFactory {

    //TODO: Every Layout from a RecyclerView must be bind here
    fun bindView(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.sale_item_layout -> SaleViewHolder(view)
            R.layout.product_item_layout->ProductViewHolder(view)
            R.layout.client_item_layout->ClientViewHolder(view)
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

    internal class ClientViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), GenericRecyclerViewAdapter.Binder<Client> {
        private val clientEt: EditText = itemView.findViewById(R.id.client_et)
        private val email: EditText = itemView.findViewById(R.id.email_et)
        private val clientImg: ImageView = itemView.findViewById(R.id.client_img)
        private val callBtt: Button = itemView.findViewById(R.id.call_btt)
        private val messageBtt: Button = itemView.findViewById(R.id.message_btt)
        private val expandableLayout: ConstraintLayout = itemView.findViewById(R.id.expandable_layout)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.parent_layout)
        private var expanded: Boolean = false

        private fun isExpanded(): Boolean = expanded

        override fun bind(listObject: Client, func: () -> Unit, context: Context) {
            clientEt.setText(listObject.client)
            email.setText(listObject.email)
            Glide.with(context).load(listObject.imgUrl).into(clientImg)

            clientEt.inputType = InputType.TYPE_NULL
            email.inputType = InputType.TYPE_NULL
            expandableLayout.visibility = View.GONE



            layout.setOnClickListener {
                if (isExpanded()) {
                    expanded = false
                    expandableLayout.visibility = View.GONE
                    //g.notifyItemChanged(adapterPosition)

                }
                else {
                    expanded = true
                    expandableLayout.visibility = View.VISIBLE
                    //g.notifyItemChanged(adapterPosition)

                }
            }
            messageBtt.setOnClickListener {
               func()
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

}
