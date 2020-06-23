package com.cccm.crowingrooster.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "delivery_man"/*,foreignKeys = [
    //TODO: DeliveryMan-User
    ForeignKey(
        entity = User::class,
        parentColumns = ["user_id"],
        childColumns = ["delivery_man_id"],
        onDelete = ForeignKey.CASCADE
    )
]*/)
data class DeliveryMan(
    @PrimaryKey val delivery_man_id: String
)