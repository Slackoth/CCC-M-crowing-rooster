package com.cccm.crowingrooster.data.network.response

import com.cccm.crowingrooster.data.db.entity.Pokemon
import com.google.gson.annotations.SerializedName


data class KantoPokemon(
    val count: Int,
    val next: String,
    val previous: Any,
    @SerializedName("results")
    val pokemon: List<Pokemon>
)