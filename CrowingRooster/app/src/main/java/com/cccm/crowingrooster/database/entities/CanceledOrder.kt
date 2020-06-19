package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "canceled_order")
data class CanceledOrder(
    @PrimaryKey val canceled_order_code: String,
    @ColumnInfo val canceled_date: String
)