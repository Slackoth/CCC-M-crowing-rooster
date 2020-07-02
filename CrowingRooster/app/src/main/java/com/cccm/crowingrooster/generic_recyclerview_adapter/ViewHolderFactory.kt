package com.cccm.crowingrooster.generic_recyclerview_adapter

import android.content.Context
import android.text.InputType
import android.view.View
import android.widget.*
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cccm.crowingrooster.*
import com.cccm.crowingrooster.data.db.entities.SellerClient
//import com.cccm.crowingrooster.database.entities.Buyer
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.*


object ViewHolderFactory {

    //TODO: Every Layout from a RecyclerView must be bind here
    fun bindView(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.sale_item_layout -> SaleViewHolder(
                view
            )
            R.layout.sale_details_item_layout -> OngoingSaleViewHolder(
                view
            )
            R.layout.product_item_layout -> ProductViewHolder(
                view
            )
            R.layout.chart_item_layout -> ChartViewHolder(
                view
            )
            R.layout.client_item_layout -> ClientViewHolder(
                view
            )
            R.layout.order_item_layout -> OrderViewHolder(
                view
            )
            R.layout.canceled_order_item_layout -> CanceledOrderViewHolder(
                view
            )
            R.layout.order_details_item -> OngoingOrderViewHolder(
                view
            )
            R.layout.chat_item_layout -> ChatOrderView(
                view
            )
            R.layout.open_orders_item_layout -> OpenOrderViewHolder(
                view
            )
            R.layout.searchbt_item_layout -> BatteryViewHolder(
                view
            )

            else -> SaleViewHolder(
                view
            )
        }
    }


    //TODO: Every element from a Layout from a RecyclerView must be bind to its respective view here
    class SaleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericRecyclerViewAdapter.Binder<Sale> {

        private val clientEt: EditText = itemView.findViewById(R.id.client_et)
        private val totalEt: EditText = itemView.findViewById(R.id.total_et)
        private val dateEt: EditText = itemView.findViewById(R.id.date_et)
        private val product: ImageView = itemView.findViewById(R.id.img)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.sale_item_layout)

        override fun bind(listObject: Sale, onClickLayout: () -> Unit, context: Context) {
            clientEt.setText(listObject.client)
            totalEt.setText(listObject.total.toString())
            dateEt.setText(listObject.date)
            Glide.with(product.context).load(listObject.imgUrl).into(product)

            clientEt.inputType = InputType.TYPE_NULL
            totalEt.inputType = InputType.TYPE_NULL
            dateEt.inputType = InputType.TYPE_NULL

            layout.setOnClickListener {
                onClickLayout()
            }
        }
    }

    internal class OngoingSaleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericRecyclerViewAdapter.Binder<SaleDetails> {

        private val quantityTv: TextView = itemView.findViewById(R.id.quantity_tv)
        private val modelTv: TextView = itemView.findViewById(R.id.model_tv)

        override fun bind(listObject: SaleDetails, func: () -> Unit, context: Context) {
            quantityTv.text = listObject.quantity.toString()
            modelTv.text = listObject.model
        }

    }

    internal class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericRecyclerViewAdapter.Binder<SellerClient> {
        private val clientEt: EditText = itemView.findViewById(R.id.client_et)
        private val email: EditText = itemView.findViewById(R.id.email_et)
        private val clientImg: ImageView = itemView.findViewById(R.id.client_img)
        private val callBtt: Button = itemView.findViewById(R.id.call_btt)
        private val messageBtt: Button = itemView.findViewById(R.id.message_btt)
        private val expandableLayout: ConstraintLayout =
            itemView.findViewById(R.id.expandable_layout)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.parent_layout)
        private var expanded: Boolean = false

        private fun isExpanded(): Boolean = expanded

        override fun bind(listObject: /*Client*/SellerClient, func: () -> Unit, context: Context) {
//            clientEt.setText(listObject.client)
//            email.setText(listObject.email)
//            Glide.with(clientImg.context).load(listObject.imgUrl).into(clientImg)

            clientEt.setText(listObject.name)
            email.setText(listObject.email)

            clientEt.inputType = InputType.TYPE_NULL
            email.inputType = InputType.TYPE_NULL
            expandableLayout.visibility = View.GONE

            layout.setOnClickListener {
                if (isExpanded()) {
                    expanded = false
                    expandableLayout.visibility = View.GONE

                } else {
                    expanded = true
                    expandableLayout.visibility = View.VISIBLE
                }
            }
            messageBtt.setOnClickListener {
                func()
            }
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericRecyclerViewAdapter.Binder<Product> {


        private val product: ImageView = itemView.findViewById(R.id.Product_img_rv)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.product_item_layout)
        private val desc: TextView = itemView.findViewById(R.id.ProductDesc_et)
        private val ProdTitle: TextView = itemView.findViewById(R.id.productTitle_et)

        override fun bind(listObject: Product, onClickLayout: () -> Unit, context: Context) {
            Glide.with(context).load(listObject.ProductImg).into(product)
            desc.setText(listObject.ProductDesc)
            ProdTitle.setText(listObject.PrductTitle)

            desc.inputType = InputType.TYPE_NULL
            ProdTitle.inputType = InputType.TYPE_NULL

            layout.setOnClickListener {
                onClickLayout()
            }

        }
    }


    class ChartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericRecyclerViewAdapter.Binder<ProductChart> {


        private val product: ImageView = itemView.findViewById(R.id.Product_img_ch)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.chart_item_layout)
        private val desc: TextView = itemView.findViewById(R.id.ProductDesc_chr)
        private val ProdTitle: TextView = itemView.findViewById(R.id.productTitle_ch)
        private val quantity: EditText = itemView.findViewById(R.id.quantity_text_ch)

        override fun bind(listObject: ProductChart, onClickLayout: () -> Unit, context: Context) {
            Glide.with(context).load(listObject.imgUrlCh).into(product)
            desc.text = listObject.ProductDesc
            ProdTitle.text = listObject.PrductTitle
            quantity.setText(listObject.quantity.toString())

            quantity.inputType = InputType.TYPE_NULL

        }

    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericRecyclerViewAdapter.Binder<Order> {

        private val orderEt: EditText = itemView.findViewById(R.id.orden_et)
        private val dateEt: EditText = itemView.findViewById(R.id.date_et)
        private val quantityEt: EditText = itemView.findViewById(R.id.quantity_et)
        private val product: ImageView = itemView.findViewById(R.id.img)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.order_item_layout)

        override fun bind(listObject: Order, onClickLayout: () -> Unit, context: Context) {
            orderEt.setText(listObject.num_order.toString())
            dateEt.setText(listObject.date)
            quantityEt.setText(listObject.quantity.toString())
            Glide.with(context).load(listObject.imgUrl).into(product)

            orderEt.inputType = InputType.TYPE_NULL
            dateEt.inputType = InputType.TYPE_NULL
            quantityEt.inputType = InputType.TYPE_NULL
            layout.setOnClickListener {
                onClickLayout()
            }


        }
    }

    /*class CanceledOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericRecyclerViewAdapter.Binder<Canceled_Order> {

        private val dateEt: EditText = itemView.findViewById(R.id.date_et)
        private val quantityEt: EditText = itemView.findViewById(R.id.quantity_et)
        private val product: ImageView = itemView.findViewById(R.id.img)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.canceled_order_item_layout)

        override fun bind(listObject: Canceled_Order, onClickLayout: () -> Unit, context: Context) {

            dateEt.setText(listObject.date)
            quantityEt.setText(listObject.quantity.toString())
            Glide.with(context).load(listObject.imgUrl).into(product)


            dateEt.inputType = InputType.TYPE_NULL
            quantityEt.inputType = InputType.TYPE_NULL
            layout.setOnClickListener {
                onClickLayout()
            }


        }
    }*/


    internal class CanceledOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericRecyclerViewAdapter.Binder<Canceled_Order> {

        private val usrimg: ImageView = itemView.findViewById(R.id.img)
        private val canceledOrderLayout: ConstraintLayout = itemView.findViewById(R.id.canceled_order_item_layout)
        private val dateEt: EditText = itemView.findViewById(R.id.date_et)
        private val quantityEt: EditText = itemView.findViewById(R.id.quantity_et)



        override fun bind(listObject: Canceled_Order, onClickLayout: () -> Unit, context: Context) {

            Glide.with(context).load(listObject.imgUrl).into(usrimg)
            dateEt.setText(listObject.date)
            quantityEt.setText(listObject.quantity.toString())

            canceledOrderLayout.setOnClickListener {
                onClickLayout()
            }

        }

    }


    internal class OngoingOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericRecyclerViewAdapter.Binder<OrderDetails> {

        private val quantityTv: TextView = itemView.findViewById(R.id.quantity_tv)
        private val modelTv: TextView = itemView.findViewById(R.id.model_tv)

        override fun bind(listObject: OrderDetails, func: () -> Unit, context: Context) {
            quantityTv.text = listObject.quantity.toString()
            modelTv.text = listObject.model
        }

    }


    class ChatOrderView(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericRecyclerViewAdapter.Binder<Chat> {

        private val usrimg: ImageView = itemView.findViewById(R.id.profile_img)
        private val usrname: TextView = itemView.findViewById(R.id.chatname_text)
        private val chatmsge: TextView = itemView.findViewById(R.id.chatlastmss_text)
        private val mssgequant: TextView = itemView.findViewById(R.id.unread_messages_text)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.chat_item_layout)

        override fun bind(listObject: Chat, onClickLayout: () -> Unit, context: Context) {
            Glide.with(context).load(listObject.ppimg).into(usrimg)
            usrname.text = listObject.username
            chatmsge.text = listObject.Mssge
            mssgequant.text = listObject.unreadmmsge.toString()

            layout.setOnClickListener{
                onClickLayout()
            }

        }

    }

    class OpenOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericRecyclerViewAdapter.Binder<OpenOrder> {
        private val openOrderDateEt: EditText = itemView.findViewById(R.id.oo_date_et)
        private val openOrderAddressEt: EditText = itemView.findViewById(R.id.oo_address_et)
        private val openOrderImg: ImageView = itemView.findViewById(R.id.oo_ib)
        private val openOrderLayout: ConstraintLayout = itemView.findViewById(R.id.oo_item_layout)

        override fun bind(listObject: OpenOrder, onClickLayout: () -> Unit, context: Context) {
            openOrderDateEt.setText(listObject.OrderDate)
            openOrderAddressEt.setText(listObject.OrderAddress)
            Glide.with(context).load(listObject.OrderImg).into(openOrderImg)

            openOrderDateEt.inputType = InputType.TYPE_NULL
            openOrderAddressEt.inputType = InputType.TYPE_NULL

            openOrderLayout.setOnClickListener {
                onClickLayout()
            }
        }
    }


    class BatteryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericRecyclerViewAdapter.Binder<Battery> {

        private val modeloEt: EditText = itemView.findViewById(R.id.modelo_Et)
        private val voltajeEt: EditText = itemView.findViewById(R.id.voltaje_Et)
        private val CCAEt: EditText = itemView.findViewById(R.id.CCA_Et)
        private val capacidadEt: EditText = itemView.findViewById(R.id.capacidad_Et)
        private val batteryimg: ImageView = itemView.findViewById(R.id.img)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.searchbt_item_layout)

        override fun bind(listObject: Battery, onClickLayout: () -> Unit, context: Context) {
            modeloEt.setText(listObject.modelo)
            voltajeEt.setText(listObject.voltaje)
            CCAEt.setText(listObject.CCA)
            capacidadEt.setText(listObject.capacidad)
            Glide.with(batteryimg.context).load(listObject.imgUrl).into(batteryimg)

            modeloEt.inputType = InputType.TYPE_NULL
            voltajeEt.inputType = InputType.TYPE_NULL
            CCAEt.inputType = InputType.TYPE_NULL
            capacidadEt.inputType = InputType.TYPE_NULL

            layout.setOnClickListener {
                onClickLayout()
            }
        }
    }


}
