package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.Quality

@Dao
interface QualityDao {
    @Insert
    fun insertQuality(quality: Quality)

    @Query("SELECT * FROM quality")
    fun getAll(): MutableList<Quality>
}