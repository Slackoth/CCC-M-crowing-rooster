package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.Seller

@Dao
interface SellerDao {
    @Insert
    fun insertSeller(seller: Seller)
}