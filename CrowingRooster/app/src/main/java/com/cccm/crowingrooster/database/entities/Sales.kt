package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "sale",foreignKeys = [
    //TODO: Sale-Seller
    ForeignKey(
        entity = Seller::class,
        parentColumns = ["seller_id"],
        childColumns = ["seller_owner"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Sales(
    @PrimaryKey val id: String,
    @ColumnInfo val state: String,
    @ColumnInfo val seller_owner: String
)