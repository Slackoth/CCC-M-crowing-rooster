package com.cccm.crowingrooster.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.data.db.entity.Pokemon
import com.cccm.crowingrooster.data.unitlocalized.BulbasaurKantoPokemon
import com.cccm.crowingrooster.data.unitlocalized.CharmanderKantoPokemon

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateInsert(pokemon: List<Pokemon>)

    @Query("SELECT * FROM pokemon WHERE name = :limit")
    fun getBulbasaur(limit: String): LiveData<BulbasaurKantoPokemon>

    @Query("SELECT * FROM pokemon WHERE name = :limit")
    fun getCharmander(limit: String): LiveData<CharmanderKantoPokemon>

    @Query("DELETE FROM pokemon")
    fun clean()

}