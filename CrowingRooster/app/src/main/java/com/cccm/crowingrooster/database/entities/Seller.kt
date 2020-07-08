package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "seller")
data class Seller(
    @Json(name = "codigo")
    @PrimaryKey
    val code: String,
    @Json(name = "nombre")
    val name: String,
    val email: String,
    val img: String,
    @Json(name = "telefono")
    val phone: String
)