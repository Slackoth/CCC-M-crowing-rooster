package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "successful_order",foreignKeys = [
    //TODO: Successful Order-Payment Method
    ForeignKey(
        entity = PaymentMethod::class,
        parentColumns = ["payment_id"],
        childColumns = ["payment_method"]
        //onDelete = ForeignKey.CASCADE
    )
])
data class SuccessfulOrder(
    @PrimaryKey val successful_order_code: String,
    @ColumnInfo val delivery_date: String,
    @ColumnInfo val delivery_hour: String,
    @ColumnInfo val total_price: Int,
    @ColumnInfo val payment_method: Int
)