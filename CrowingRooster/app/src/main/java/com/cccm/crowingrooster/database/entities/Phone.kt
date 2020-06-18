package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "phone",foreignKeys = [
    //TODO: Seller-Phone
    ForeignKey(
        entity = Seller::class,
        parentColumns = ["seller_id"],
        childColumns = ["seller_owner"],
        onDelete = ForeignKey.CASCADE
    ),
    //TODO: Delivery man-Phone
    ForeignKey(
        entity = DeliveryMan::class,
        parentColumns = ["delivery_man_id"],
        childColumns = ["delivery_man_owner"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Phone(
    @PrimaryKey(autoGenerate = true) val phone_id: Int,
    @ColumnInfo val phone_number: String,
    @ColumnInfo val seller_owner: String?,
    @ColumnInfo val delivery_man_owner: String?
)