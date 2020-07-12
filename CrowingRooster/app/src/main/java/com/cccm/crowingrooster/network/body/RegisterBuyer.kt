package com.cccm.crowingrooster.network.body

import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class RegisterBuyer(
    var username: String,
    var name: String,
    var company: String,
    var dui: String,
    var email: String,
    var phone: String,
    var img: String,
    var password: String
)