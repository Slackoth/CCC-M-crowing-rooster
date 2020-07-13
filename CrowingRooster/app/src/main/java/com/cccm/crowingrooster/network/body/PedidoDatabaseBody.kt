package com.cccm.crowingrooster.network.body

import androidx.room.Entity
import androidx.room.PrimaryKey



data class PedidoDatabaseBody(
    var comprador:String,
    var cantidad:Int,
    var orden:String,
    var bateria:Int

)
