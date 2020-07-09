package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.Seller

@Dao
interface SellerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(seller: Seller)

    @Query("SELECT * FROM seller WHERE code = :code")
    fun getSeller(code: String?): LiveData<Seller>
}