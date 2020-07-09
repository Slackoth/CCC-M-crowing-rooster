package com.cccm.crowingrooster.database.daos.order

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.order.OrderPreview

@Dao
interface OrderPreviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(orderPreview: OrderPreview)

    @Query("SELECT * FROM order_preview where state = :state")
    fun getAll(state: String): LiveData<List<OrderPreview>>
}