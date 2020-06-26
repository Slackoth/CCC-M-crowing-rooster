package com.cccm.crowingrooster.data.network

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.data.ApixuKantoPokemonApiService
import com.cccm.crowingrooster.data.network.response.KantoPokemon
import com.cccm.crowingrooster.internal.NoConnectivityException

class PokemonNetworkDataSourceImpl(
    private val apixuKantoPokemonApiService: ApixuKantoPokemonApiService
) : PokemonNetworkDataSource {
    private val _downloadedPokemon: MutableLiveData<KantoPokemon> = MutableLiveData()
    override val downloadedPokemon: LiveData<KantoPokemon>
        get() = _downloadedPokemon

    @SuppressLint("LogNotTimber")
    override suspend fun fetchPokemon(limit: Int) {
        try {
            val fetchedPokemon = apixuKantoPokemonApiService
                .getAllAsync(limit)
            _downloadedPokemon.postValue(fetchedPokemon)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity","No internet connection...",e)
        }
    }
}