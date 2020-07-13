package com.cccm.crowingrooster.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "batteries_catalogue")
data class Catalogue (
    @PrimaryKey val id_bateria:Int,
    var modelo:String,
    var dimensiones:String,
    var direccion:String,
    var capacidad_reserva:Int,
    var tipo:String,
    var amperaje:Int,
    var cca:Int,
    var product_img:String
)