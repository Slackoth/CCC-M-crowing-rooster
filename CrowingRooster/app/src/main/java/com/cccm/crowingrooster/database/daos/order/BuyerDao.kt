package com.cccm.crowingrooster.database.daos.order

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.order.Buyer

@Dao
interface BuyerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(buyer: Buyer)

    @Query("SELECT * FROM buyer WHERE code = :code")
    fun getBuyer(code: String?): LiveData<Buyer>
}