package com.cccm.crowingrooster.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "seller_client")
data class SellerClient(
    @Json(name = "comprador_codigo")
    @PrimaryKey
    val code: String,
    @Json(name = "nombre")
    val name: String,
    val email: String,
    @Json(name = "nombre_empresa")
    val company: String,
    val img: String,
    @Json(name = "telefono")
    val phone: String
)