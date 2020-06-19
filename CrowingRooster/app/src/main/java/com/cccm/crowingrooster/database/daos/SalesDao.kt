package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.Sales

@Dao
interface SalesDao {
    @Insert
    fun insertSale(sales: Sales)
}