package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "buyer",foreignKeys = [
    //TODO: Buyer-Company
    ForeignKey(
        entity = Company::class,
        parentColumns = ["company_id"],
        childColumns = ["company"],
        onDelete = ForeignKey.NO_ACTION
    )
])
data class Buyer(
    @PrimaryKey val buyer_id: String,
    @ColumnInfo val dui: String,
    @ColumnInfo val email: String,
    @ColumnInfo val company: Int
)