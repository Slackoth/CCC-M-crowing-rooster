package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "polarity")
data class Polarity(
    @PrimaryKey(autoGenerate = true) val polarity_id: Int = 0,
    @ColumnInfo val type: String
)