package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.Buyer

@Dao
interface BuyerDao {
    @Insert
    fun insertBuyer(buyer: Buyer)

    @Query("SELECT * FROM buyer WHERE buyer_id = :id")
    fun getBuyer(id: String): Buyer

    @Query("SELECT * FROM buyer")
    fun getClientsForSeller(): MutableList<Buyer>

}