package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.SuccessfulSaleAndDelivery

@Dao
interface SuccessfulSaleAndDeliveryDao {
    @Insert
    fun insertSuccessfulSaleAndDelivery(successfulSaleAndDelivery: SuccessfulSaleAndDelivery)
}