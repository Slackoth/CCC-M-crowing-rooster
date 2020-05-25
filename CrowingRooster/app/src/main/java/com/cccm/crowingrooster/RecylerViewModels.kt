package com.cccm.crowingrooster

//TODO: Every element inside a Layout for a RecylerView must be transformed into a data class

data class Sale(var client: String, var model: String, var quantity: Int, var imgUrl: String)
data class Client(var client: String, var email: String, var imgUrl: String)
data class Product(var PrductTitle:String, var ProductDesc:String, var ProductImg:String)