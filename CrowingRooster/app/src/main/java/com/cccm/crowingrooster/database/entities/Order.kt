package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "order",foreignKeys = [
    //TODO: Order-Seller
    ForeignKey(
        entity = Seller::class,
        parentColumns = ["seller_id"],
        childColumns = ["seller_owner"],
        onDelete = ForeignKey.CASCADE
    ),
    //TODO: Order-Buyer
    ForeignKey(
        entity = Buyer::class,
        parentColumns = ["buyer_id"],
        childColumns = ["buyer_owner"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Order(
    @PrimaryKey val order_code: String,
    @ColumnInfo val state: String,
    @ColumnInfo val seller_owner: String,
    @ColumnInfo val buyer_owner: String
)