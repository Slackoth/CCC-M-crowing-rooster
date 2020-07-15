package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.SalePreview

@Dao
interface SalePreviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(salePreview: SalePreview)

    @Query("SELECT * FROM sale_preview where seller = :seller AND state = :state")
    fun getAll(state: String,seller: String?): LiveData<List<SalePreview>>

}