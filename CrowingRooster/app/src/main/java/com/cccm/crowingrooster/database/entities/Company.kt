package com.cccm.crowingrooster.database.entities

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor

@Entity(tableName = "company")
data class Company(
    @PrimaryKey(autoGenerate = true) val company_id: Int = 0,
    @ColumnInfo val company_name: String
)

