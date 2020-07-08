package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlin.reflect.jvm.internal.impl.renderer.KeywordStringsGenerated


@Entity
data class Battery(
    @PrimaryKey val id_bateria:Int,
    val modelo:String,
    val dimensiones:String,
    val direccion:String,
    val capacidad_reserva:Int,
    val tipo:String,
    val amperaje:Int,
    val cca:Int
)

