package com.cccm.crowingrooster.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "user")
data class User(
    @Json(name = "codigo")
    @PrimaryKey var code: String,
    var email: String,
    @Json(name = "tipo")
    var type: String,
    var img: String
)