package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "successful_sale",foreignKeys = [
    //TODO: Successful Sale-Payment Method
    ForeignKey(
        entity = PaymentMethod::class,
        parentColumns = ["payment_id"],
        childColumns = ["payment_method"]
        //onDelete = ForeignKey.CASCADE
    )
])
data class SuccessfulSale(
    @PrimaryKey val successful_sale_id: String,
    @ColumnInfo val successful_sale_date: String,
    @ColumnInfo val price: Double,
    @ColumnInfo val payment_method: Int
)
