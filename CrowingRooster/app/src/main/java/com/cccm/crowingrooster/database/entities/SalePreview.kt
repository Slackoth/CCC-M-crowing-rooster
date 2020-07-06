package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "sale_preview")
data class SalePreview(
    @Json(name = "nombre")
    @PrimaryKey val name: String,
    @Json(name = "fecha_pedido")
    @ColumnInfo(name = "pending_date")
    val pendingDate: String,
    val total: Int,
    val img: String

)