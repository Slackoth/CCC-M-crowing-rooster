package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "delivery_state")
data class DeliveryState(
    @PrimaryKey(autoGenerate = true) val state_id: Int,
    @ColumnInfo val state: String
)