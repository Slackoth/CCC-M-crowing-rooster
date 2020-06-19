package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.SuccessfulSale

@Dao
interface SuccessfulSaleDao {
    @Insert
    fun insertSuccessfulSale(successfulSale: SuccessfulSale)
}