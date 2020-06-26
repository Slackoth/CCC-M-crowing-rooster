package com.cccm.crowingrooster.data.repository

import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.data.unitlocalized.UnitSpecificKantoPokemon

interface KantoPokemonRepository {
    suspend fun getAll(bulbasaur: Boolean): LiveData<out UnitSpecificKantoPokemon>
    suspend fun cleanAll()
}