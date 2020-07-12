package com.cccm.crowingrooster.database.entities.order

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "buyer")
data class Buyer(
    @Json(name = "codigo")
    @PrimaryKey var code: String,
    @Json(name = "nombre")
    var name: String,
    @Json(name = "empresa")
    var company: String,
    var dui: String,
    var email: String,
    @Json(name = "telefono")
    var phone: String,
    var img: String
)