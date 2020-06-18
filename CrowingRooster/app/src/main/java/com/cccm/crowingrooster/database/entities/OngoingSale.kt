package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ongoing_sale")
data class OngoingSale(
    @PrimaryKey val ongoing_sale_id: String,
    @ColumnInfo val ongoing_sale_date: String
)