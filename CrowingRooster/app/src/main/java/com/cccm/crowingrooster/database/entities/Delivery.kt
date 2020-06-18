package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "delivery",foreignKeys = [
        //TODO: Delivery-Delivery State
        ForeignKey(
            entity = DeliveryState::class,
            parentColumns = ["state_id"],
            childColumns = ["state"]
            //onDelete = ForeignKey.CASCADE
        )
])
data class Delivery(
    @PrimaryKey(autoGenerate = true) val delivery_id: Int,
    @ColumnInfo val state: Int
)