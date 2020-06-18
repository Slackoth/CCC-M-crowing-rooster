package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.OngoingOrder

@Dao
interface OngoingOrderDao {
    @Insert
    fun insertOngoingOrder(ongoingOrder: OngoingOrder)
}