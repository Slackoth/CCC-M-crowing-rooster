package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.DeliveryState

@Dao
interface DeliveryStateDao {
    @Insert
    fun insertDeliveryState(deliveryState: DeliveryState)

    @Query("SELECT * FROM delivery_state")
    fun getAll(): MutableList<DeliveryState>
}