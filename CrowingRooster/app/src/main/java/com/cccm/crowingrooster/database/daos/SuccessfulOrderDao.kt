package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.SuccessfulOrder

@Dao
interface SuccessfulOrderDao {
    @Insert
    fun insertSuccessfulOrder(successfulOrder: SuccessfulOrder)
}