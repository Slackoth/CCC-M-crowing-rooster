package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ongoing_order")
data class OngoingOrder(
    @PrimaryKey val ongoing_order_code: String,
    @ColumnInfo val submitted_date: String
)