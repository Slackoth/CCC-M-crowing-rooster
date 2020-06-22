package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.Company

@Dao
interface CompanyDao {
    @Insert
    fun insertCompany(company: Company)

    @Query("SELECT * FROM company")
    fun getAll(): MutableList<Company>

    @Query("SELECT * FROM company WHERE company_id = :id")
    fun getCompany(id: Int): Company

}