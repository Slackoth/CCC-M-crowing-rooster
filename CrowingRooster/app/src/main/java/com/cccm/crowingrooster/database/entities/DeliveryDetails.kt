package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "successful_delivery_details")
data class DeliveryDetails (
    @PrimaryKey
    @Json(name = "codigo_repartidor")
    var deliveryManCode: String,
    @Json(name = "id_entrega")
    var entregaId: Int = 0,
    @Json(name = "direccion_entrega")
    var address: String = "",
    @Json(name = "precio")
    var price: String = "",
    @Json(name = "metodo")
    var payment: String = "",
    @Json(name = "cantidad_total")
    @ColumnInfo(name = "total_quantity")
    var totalQuantity: Int = 0,
    @Json(name = "estado")
    var state: String = "",
    @Json(name = "pedidos")
    @Ignore var minidelivery: List<DeliveryMiniOrders> = listOf()
) {
    constructor() : this("",0,"","","",0,"", listOf())
}