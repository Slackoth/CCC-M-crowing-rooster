package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.Quality

@Dao
interface QualityDao {
    @Insert
    fun insertQuality(quality: Quality)
}