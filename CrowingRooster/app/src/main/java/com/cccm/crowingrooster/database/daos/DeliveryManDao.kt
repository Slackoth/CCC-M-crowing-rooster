package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.DeliveryMan

@Dao
interface DeliveryManDao {
    @Insert
    fun insertDeliveryMan(deliveryMan: DeliveryMan)
}