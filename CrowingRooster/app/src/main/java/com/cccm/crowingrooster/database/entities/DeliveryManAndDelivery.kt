package com.cccm.crowingrooster.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "delivery_man_and_delivery",primaryKeys = ["delivery_man","delivery"],foreignKeys = [
    //TODO: Delivery-DeliveryManAndDelivery
    ForeignKey(
        entity = Delivery::class,
        parentColumns = ["delivery_id"],
        childColumns = ["delivery"],
        onDelete = ForeignKey.CASCADE
    ),
    //TODO: Delivery Man-DeliveryManAndDelivery
    ForeignKey(
        entity = DeliveryMan::class,
        parentColumns = ["delivery_man_id"],
        childColumns = ["delivery_man"],
        onDelete = ForeignKey.CASCADE
    )
])
data class DeliveryManAndDelivery(
    val delivery_man: String,
    val delivery: Int
)