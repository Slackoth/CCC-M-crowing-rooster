package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.Polarity

@Dao
interface PolarityDao {
    @Insert
    fun insertPolarity(polarity: Polarity)

    @Query("SELECT * FROM polarity")
    fun getAll(): MutableList<Polarity>
}