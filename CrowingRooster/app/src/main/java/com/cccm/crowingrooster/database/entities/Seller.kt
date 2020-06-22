package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "seller"/*,foreignKeys = [
    //TODO: Seller-User
    ForeignKey(
        entity = User::class,
        parentColumns = ["user_id"],
        childColumns = ["seller_id"],
        onDelete = ForeignKey.CASCADE
    )
]*/)
data class Seller(
    @PrimaryKey val seller_id: String,
    @ColumnInfo val email: String
)