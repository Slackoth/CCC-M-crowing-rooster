package com.cccm.crowingrooster.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "chart")
data class Pedido (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var cantidad_bateria:Int,
    var id_bateria:Int,
    var id_usuario:String,
    var img_bateria:String,
    var desc_bateria:String,
    var titulo:String
)