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

//    @Query("SELECT * FROM buyer WHERE buyer_id = :id")
//    fun getBuyer(id: String): LiveData<Buyer>

    @Query("SELECT * FROM buyer")
    //@Query("SELECT u.name, b.email FROM buyer b INNER JOIN user u ON b.buyer_id = u.user_id ")
    fun getAll(): LiveData<MutableList<Buyer>>

}