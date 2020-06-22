package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.OngoingSale

@Dao
interface OngoingSaleDao {
    @Insert
    fun insertOngoingSale(ongoingSale: OngoingSale)
}