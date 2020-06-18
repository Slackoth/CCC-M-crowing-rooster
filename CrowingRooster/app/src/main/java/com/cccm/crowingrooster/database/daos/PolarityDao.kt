package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.Polarity

@Dao
interface PolarityDao {
    @Insert
    fun insertPolarity(polarity: Polarity)
}