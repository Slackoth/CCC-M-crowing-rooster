package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.CanceledOrder

@Dao
interface CanceledOrderDao {
    @Insert
    fun insertCanceledOrder(canceledOrder: CanceledOrder)
}