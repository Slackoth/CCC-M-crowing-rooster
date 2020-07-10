package com.cccm.crowingrooster.database.entities.order

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "order_mini_order")
data class OrderMiniOrder(
    @Json(name = "estado")
    var state: String,
    @Json(name = "modelo")
    var model: String,
    @Json(name = "calidad")
    var quality: String,
    @Json(name = "cantidad")
    var quantity: Int,
    @Json(name = "id_orden")
    @ColumnInfo(name = "order_id")
    var orderId: String,
    @Json(name = "id_pedido")
    @PrimaryKey @ColumnInfo(name = "id")
    var miniOrderId: Long,
    @Json(name = "polaridad")
    var polarity: String
)