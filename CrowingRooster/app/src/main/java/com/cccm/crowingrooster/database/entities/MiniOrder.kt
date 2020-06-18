package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "mini_order",foreignKeys = [
    //TODO: Mini Order-Buyer
    ForeignKey(
        entity = Buyer::class,
        parentColumns = ["buyer_id"],
        childColumns = ["buyer_owner"],
        onDelete = ForeignKey.CASCADE
    ),
    //TODO: Mini Order-Battery
    ForeignKey(
        entity = Battery::class,
        parentColumns = ["battery_id"],
        childColumns = ["order_battery"]
        //onDelete = ForeignKey.CASCADE
    ),
    //TODO: Mini Order-Order
    ForeignKey(
        entity = Order::class,
        parentColumns = ["order_code"],
        childColumns = ["order_owner"],
        onDelete = ForeignKey.CASCADE
    )
])
data class MiniOrder(
    @PrimaryKey(autoGenerate = true) val order_number: Int,
    @ColumnInfo val buyer_owner: String,
    @ColumnInfo val order_battery: Int,
    @ColumnInfo val order_owner: Int
)