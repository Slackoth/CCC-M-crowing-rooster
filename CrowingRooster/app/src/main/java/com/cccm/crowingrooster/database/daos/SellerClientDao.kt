package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.SellerClient

@Dao
interface SellerClientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sellerClient: SellerClient)

    @Query("SELECT * FROM seller_client")
    fun getAll(): LiveData<List<SellerClient>>
}