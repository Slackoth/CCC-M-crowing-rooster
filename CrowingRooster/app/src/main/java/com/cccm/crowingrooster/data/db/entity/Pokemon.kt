package com.cccm.crowingrooster.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey val name: String,
    val url: String
)