package com.cccm.crowingrooster.database.entities.order

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "order_details")
data class OrderDetails(
    @Json(name = "codigo")
    @PrimaryKey var code: String,
    @Json(name = "nombre")
    var name: String,
    var email: String,
    @Json(name = "fecha")
    var date: String,
    @Json(name = "precio")
    var price: String,
    @Json(name = "metodo")
    var payment: String,
    var total: String,
    @Json(name = "telefono")
    var phone: String,
    @Json(name = "pedidos")
    @Ignore var orderMiniOrder: List<OrderMiniOrder>
) {
    constructor(): this("","","","","","","","", listOf())

}