package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.DeliveryDetails

@Dao
interface DeliveryDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(deliveryDetails: DeliveryDetails)

    @Query("select * from successful_delivery_details where deliveryManCode = :code")
    fun getSpecific(code:String?): LiveData<DeliveryDetails>
}