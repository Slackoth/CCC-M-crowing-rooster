package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "sale_preview")
data class SalePreview(
    @Json(name = "id_venta")
    @PrimaryKey var saleId: String,
    @Json(name = "nombre")
    var name: String,
    @Json(name = "fecha")
    var date: String,
    var total: Int,
    var img: String,
    @Json(name = "codigo_orden")
    var orderCode: String,
    @Json(name = "estado")
    var state: String


)