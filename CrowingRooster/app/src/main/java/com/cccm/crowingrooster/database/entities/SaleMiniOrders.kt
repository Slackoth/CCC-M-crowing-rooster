package com.cccm.crowingrooster.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "sale_mini_orders")
data class SaleMiniOrders(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Json(name = "estado")
    val state: String,
    @Json(name = "modelo")
    val model: String,
    @Json(name = "calidad")
    val quality: String,
    @Json(name = "cantidad")
    val quantity: Int,
    @Json(name = "polaridad")
    val polarity: String
)
