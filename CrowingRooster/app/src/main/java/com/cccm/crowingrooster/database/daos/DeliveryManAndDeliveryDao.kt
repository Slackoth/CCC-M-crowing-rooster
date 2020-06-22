package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.DeliveryManAndDelivery

@Dao
interface DeliveryManAndDeliveryDao {
    @Insert
    fun insertDeliveryManAndDelivery(deliveryManAndDelivery: DeliveryManAndDelivery)
}