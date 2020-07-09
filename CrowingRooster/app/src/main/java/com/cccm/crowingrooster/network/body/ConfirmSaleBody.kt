package com.cccm.crowingrooster.network.body

data class ConfirmSaleBody(
    var price: Double,
    var date: String,
    var hour: String,
    var address: String,
    var payment: String
)
