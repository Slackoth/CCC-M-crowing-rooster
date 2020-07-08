package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "sale_mini_orders")
data class SaleMiniOrders(
    //@PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Json(name = "estado")
    var state: String,
    @Json(name = "modelo")
    var model: String,
    @Json(name = "calidad")
    var quality: String,
    @Json(name = "cantidad")
    var quantity: Int,
    @Json(name = "id_venta")
    @ColumnInfo(name = "sale_id")
    var saleId: String,
    @Json(name = "comprador")
    var buyer: String,
    @Json(name = "id_pedido")
    @PrimaryKey @ColumnInfo(name = "id")
    var miniOrderId: Long,
    @Json(name = "polaridad")
    var polarity: String

)
