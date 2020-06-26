package com.cccm.crowingrooster.data.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.data.db.dao.PokemonDao
import com.cccm.crowingrooster.data.network.PokemonNetworkDataSource
import com.cccm.crowingrooster.data.network.response.KantoPokemon
import com.cccm.crowingrooster.data.unitlocalized.UnitSpecificKantoPokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
//import java.time.ZonedDateTime
//TODO: Using this library because ZonedDateTime and DateLocalTime runs only in APIs >= 26
import org.threeten.bp.ZonedDateTime

class KantoPokemonRepositoryImpl(
    private val pokemonDao: PokemonDao,
    private val pokemonNetworkDataSource: PokemonNetworkDataSource
) : KantoPokemonRepository {

    init {
        pokemonNetworkDataSource.downloadedPokemon.observeForever {
            persistFetchedPokemon(it)
        }
    }

    override suspend fun getAll(bulbasaur: Boolean): LiveData<out UnitSpecificKantoPokemon> {
        //returns a value
        initPokemonData()
        return withContext(Dispatchers.IO) {
            return@withContext if (bulbasaur) pokemonDao.getBulbasaur("bulbasaur")
            else pokemonDao.getCharmander("charmander")
        }
    }

    override suspend fun cleanAll() {
        pokemonDao.clean()
    }

    private fun persistFetchedPokemon(fetchedPokemon: KantoPokemon) {
        //We can use it here - Returns a job
        GlobalScope.launch(Dispatchers.IO) {
            //pokemonDao.clean()
            pokemonDao.updateInsert(fetchedPokemon.pokemon)
        }
    }

    private suspend fun fetchPokemon(limit: Int = 151) {
        pokemonNetworkDataSource.fetchPokemon(limit)
    }

    private suspend fun isFetchedPokemonNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinAgo)
    }

    private suspend fun initPokemonData() {
        if (isFetchedPokemonNeeded(ZonedDateTime.now().minusHours(1)))
            fetchPokemon()
    }

}