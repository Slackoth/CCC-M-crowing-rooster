package com.cccm.crowingrooster.generic_recyclerview_adapter.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//TODO: Every element inside a Layout for a RecylerView must be transformed into a data class

data class Sale(var client: String, var date: String, var total: Int, var imgUrl: String)
data class SaleDetails(var quantity: Int, var model: String)
data class Product(var PrductTitle:String, var ProductDesc:String, var ProductImg:String)
data class ProductChart(var PrductTitle: String, var ProductDesc: String, var quantity: Int, var imgUrlCh: String)
data class Order(var num_order: Int, var quantity: Int, var imgUrl: String, var date: String)
data class Canceled_Order(var quantity: Int, var imgUrl: String, var date: String)
//data class Client(var client: String, var email: String, var imgUrl: String)
data class Chat(var username:String, var Mssge:String, var unreadmmsge:Int,var ppimg:String)
data class OpenOrder(var OrderDate: String, var OrderAddress: String, var OrderImg: String)
data class OrderDetails(var quantity: Int, var model: String)
data class Catalogue(var modelo: String, var voltaje: String, var CCA: String, var capacidad: String, var imgUrl: String)

@Parcelize
class UserDatabase(
    val uid: String,
    val status:String, val username: String, val profileImageUrl: String
): Parcelable {
    constructor() : this("", "", "","")
}

