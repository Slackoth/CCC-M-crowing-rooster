package com.cccm.crowingrooster.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "Orden"
)
data class OrdertoChart (
    @PrimaryKey
    val codigo_orden:String,
    val estado:String
)