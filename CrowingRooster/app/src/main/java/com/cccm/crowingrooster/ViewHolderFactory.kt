package com.cccm.crowingrooster

import android.app.Activity
import android.content.Context
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
        private val layout: ConstraintLayout = itemView.findViewById(R.id.sale_item_layout)

        override fun bind(listObject: Sale, fragment: Fragment, fragmentActivity: FragmentActivity) {
            clientEt.setText(listObject.client)
            modelEt.setText(listObject.model)
            quantityEt.setText(listObject.quantity.toString())
            fragment.context?.let { Glide.with(it).load(listObject.imgUrl).into(product) }

            clientEt.inputType = InputType.TYPE_NULL
            modelEt.inputType = InputType.TYPE_NULL
            quantityEt.inputType = InputType.TYPE_NULL

            layout.setOnClickListener {
                if(fragment is SuccessfulSalesFragment) {
                    onLayoutClick(fragmentActivity)
                }
                else {
                    it.findNavController().navigate(R.id.ongoingSalesDetailsFragment)
                }
            }
        }

        private fun onLayoutClick(fragmentActivity: FragmentActivity) {
            val dialog = SaleDetailsDialogFragment()
            dialog.show(fragmentActivity.supportFragmentManager, "SaleDetailsDialog")
        }

    }

}
