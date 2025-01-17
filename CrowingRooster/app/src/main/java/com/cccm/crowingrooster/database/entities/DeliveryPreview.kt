package com.cccm.crowingrooster.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "delivery_preview")
data class DeliveryPreview (
    @Json(name = "id_entrega")
    @PrimaryKey var deliveryId: Int,
    @Json(name = "direccion_entrega")
    var deliveryAddress: String,
    @Json(name = "estado")
    var state: String,
    @Json(name = "precio")
    var price: String,
    @Json(name = "metodo")
    var payment: String,
    var product_img: String,
    @Json(name = "codigo_repartidor")
    var deliveryManId: String
)