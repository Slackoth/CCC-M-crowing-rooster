package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.Buyer

@Dao
interface BuyerDao {
    @Insert
    fun insertBuyer(buyer: Buyer)
}