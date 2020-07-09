package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.SaleMiniOrders
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.Sale

@Dao
interface SaleMiniOrdersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(saleMiniOrders: SaleMiniOrders)

    @Query("SELECT * FROM sale_mini_orders WHERE state = :state and sale_id = :saleId")
    fun getAll(state: String,saleId: String?): LiveData<List<SaleMiniOrders>>
}