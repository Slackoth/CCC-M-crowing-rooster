package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.Delivery

@Dao
interface DeliveryDao {
    @Insert
    fun insertDelivery(delivery: Delivery)
}