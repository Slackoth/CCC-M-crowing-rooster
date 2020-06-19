package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "successful_sale_and_delivery",primaryKeys = ["successful_sale","delivery"],foreignKeys = [
    //TODO: Successful Sale-SuccessfulSaleAndDelivery
    ForeignKey(
        entity = SuccessfulSale::class,
        parentColumns = ["successful_sale_id"],
        childColumns = ["successful_sale"],
        onDelete = ForeignKey.CASCADE
    ),
    //TODO: Delivery-SuccessfulSaleAndDelivery
    ForeignKey(
        entity = Delivery::class,
        parentColumns = ["delivery_id"],
        childColumns = ["delivery"],
        onDelete = ForeignKey.CASCADE
    )
])
data class SuccessfulSaleAndDelivery(
    val successful_sale: String,
    val delivery: Int,
    @ColumnInfo val delivery_address: String,
    @ColumnInfo val delivery_hour: String
)