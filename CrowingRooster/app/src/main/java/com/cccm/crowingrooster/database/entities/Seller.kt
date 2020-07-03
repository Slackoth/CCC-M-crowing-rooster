package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "seller")
data class Seller(
    @Json(name = "seller_id")
    @PrimaryKey @ColumnInfo(name = "seller_id")
    val sellerId: String,
    val name: String,
    @Json(name = "phone_number")
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    val email: String
)