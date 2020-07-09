package com.cccm.crowingrooster.database.entities.order

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "order_preview")
data class OrderPreview(
    @Json(name = "codigo_orden")
    @PrimaryKey var code: String,
    @Json(name = "fecha_entrega")
    var date: String,
    @Json(name = "cantidad")
    var quantity: String,
    @Json(name = "estado")
    var state: String
)