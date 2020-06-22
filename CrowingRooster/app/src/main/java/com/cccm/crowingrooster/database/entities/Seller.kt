package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seller")
data class Seller(
    @PrimaryKey val seller_id: String,
    @ColumnInfo val email: String
)