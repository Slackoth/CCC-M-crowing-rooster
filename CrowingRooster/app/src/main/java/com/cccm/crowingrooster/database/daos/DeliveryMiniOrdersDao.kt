package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.DeliveryMiniOrders

@Dao
interface DeliveryMiniOrdersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(deliveryMiniOrders: DeliveryMiniOrders)

    @Query("select * from delivery_mini_orders where state = :state and miniDeliveryId = :id_order")
    fun getAll(state: String?, id_order: Int?): LiveData<List<DeliveryMiniOrders>>
}