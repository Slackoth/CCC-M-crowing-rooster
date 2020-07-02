package com.cccm.crowingrooster.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.data.db.entities.SellerClient

@Dao
interface SellerClientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sellerClient: SellerClient)

    @Query("SELECT * FROM seller_client")
    fun getAllSellerClients(): List<SellerClient>

    @Query("SELECT * FROM seller_client WHERE name = :n")
    fun ptm(n: String): SellerClient
}