package com.cccm.crowingrooster.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_info")
data class BatteryInfo (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var id_bateria:Int,
    var modelo :String,
    var direccion:String,
    var tipo: String
)