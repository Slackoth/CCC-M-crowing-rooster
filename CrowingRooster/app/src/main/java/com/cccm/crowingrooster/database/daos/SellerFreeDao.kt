package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.SellerFree


@Dao
interface SellerFreeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(SellerFree:SellerFree)

    @Query("SELECT * FROM seller_free" )
    fun getone(): LiveData<SellerFree>
}