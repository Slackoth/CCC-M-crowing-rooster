package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val user_id: String,
    @ColumnInfo val username: String,
    @ColumnInfo val name: String,
    @ColumnInfo val password: String,
    @ColumnInfo val type: String
)