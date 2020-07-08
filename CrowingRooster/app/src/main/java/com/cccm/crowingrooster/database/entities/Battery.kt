package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlin.reflect.jvm.internal.impl.renderer.KeywordStringsGenerated


@Entity
data class Battery(
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

