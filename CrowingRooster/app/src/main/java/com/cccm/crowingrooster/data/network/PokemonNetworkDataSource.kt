package com.cccm.crowingrooster.data.network

import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.data.network.response.KantoPokemon

interface PokemonNetworkDataSource {
    val downloadedPokemon: LiveData<KantoPokemon>

    suspend fun fetchPokemon(
        limit: Int = 151
    )



}