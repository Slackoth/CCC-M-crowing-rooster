package com.cccm.crowingrooster.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cccm.crowingrooster.data.db.dao.PokemonDao
import com.cccm.crowingrooster.data.db.entity.Pokemon

@Database(
    entities = [Pokemon::class],
    version = 1
)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var instance: PokemonDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) =
            instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PokemonDatabase::class.java,
                "pokemon_db"
            ).build()

    }
}