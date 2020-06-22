package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.Order

@Dao
interface OrderDao {
    @Insert
    fun insertOrder(order: Order)
}