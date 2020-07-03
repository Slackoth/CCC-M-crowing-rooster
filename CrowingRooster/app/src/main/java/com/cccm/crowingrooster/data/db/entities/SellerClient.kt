package com.cccm.crowingrooster.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seller_client")
data class SellerClient(
    @PrimaryKey val name: String,
    val email: String
)