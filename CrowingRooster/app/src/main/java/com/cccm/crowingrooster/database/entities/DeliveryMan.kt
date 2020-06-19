package com.cccm.crowingrooster.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "delivery_man")
data class DeliveryMan(
    @PrimaryKey val delivery_man_id: String
)