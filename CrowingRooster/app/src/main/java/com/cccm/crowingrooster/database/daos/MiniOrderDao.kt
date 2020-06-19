package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.MiniOrder

@Dao
interface MiniOrderDao {
    @Insert
    fun insertMiniOrder(miniOrder: MiniOrder)
}