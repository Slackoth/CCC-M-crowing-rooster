package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.OrdertoChart


@Dao
interface OrdertoChartDao {
    @Insert
    fun Insert(OrdertoChart:OrdertoChart)


}