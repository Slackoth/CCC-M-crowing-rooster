package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.Catalogue

@Dao
interface CatalogueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(catalogue: Catalogue)

    @Query("select * from batteries_catalogue")
    fun getAll(): LiveData<List<Catalogue>>
}