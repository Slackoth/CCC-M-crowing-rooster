package com.cccm.crowingrooster.database.daos.order

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.SaleMiniOrders
import com.cccm.crowingrooster.database.entities.order.OrderMiniOrder

@Dao
interface OrderMiniOrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(orderMiniOrder: OrderMiniOrder)

    @Query("SELECT * FROM order_mini_order WHERE state = :state and order_id = :orderId")
    fun getAll(state: String,orderId: String?): LiveData<List<OrderMiniOrder>>
}