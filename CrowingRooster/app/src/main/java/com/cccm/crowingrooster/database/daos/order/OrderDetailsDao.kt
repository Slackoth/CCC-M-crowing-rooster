package com.cccm.crowingrooster.database.daos.order

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.SaleDetails
import com.cccm.crowingrooster.database.entities.order.OrderDetails

@Dao
interface OrderDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(orderDetails: OrderDetails)

    @Query("SELECT * FROM order_details WHERE code = :code")
    fun getSpecific(code:String?): LiveData<OrderDetails>
}