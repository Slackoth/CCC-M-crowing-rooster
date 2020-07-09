package com.cccm.crowingrooster.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seller_free")
data class SellerFree(
    @PrimaryKey
    val email:String,
    val cant_pedidos:Int
)