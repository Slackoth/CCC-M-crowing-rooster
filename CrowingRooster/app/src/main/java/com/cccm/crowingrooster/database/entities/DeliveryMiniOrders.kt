package com.cccm.crowingrooster.database.entities

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "delivery_mini_orders")
data class DeliveryMiniOrders (
    @Json(name = "estado")
    var state: String,
    @Json(name = "modelo")
    var model: String,
    @Json(name = "calidad")
    var quality: String,
    @Json(name = "cantidad")
    var quantity: Int,
    @Json(name = "comprador")
    var buyer: String,
    @Json(name = "id_pedido")
    @PrimaryKey @ColumnInfo(name = "id")
    var pedidoId: Int,
    @Json(name = "polaridad")
    var polarity: String,
    @Json(name = "id_entrega")
    var miniDeliveryId: Int,
    @Json(name = "dimensiones")
    var dimensions: String
)