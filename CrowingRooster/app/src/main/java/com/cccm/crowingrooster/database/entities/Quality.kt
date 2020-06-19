package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quality")
data class Quality(
    @PrimaryKey(autoGenerate = true) val quality_id: Int = 0,
    @ColumnInfo val type: String
)