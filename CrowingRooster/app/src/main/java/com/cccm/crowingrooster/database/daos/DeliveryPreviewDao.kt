package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.DeliveryPreview

@Dao
interface DeliveryPreviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(deliveryPreview: DeliveryPreview)

    @Query("select * from delivery_preview where state = :state and deliveryManId = :code")
    fun getAll(code: String, state: String): LiveData<List<DeliveryPreview>>
}