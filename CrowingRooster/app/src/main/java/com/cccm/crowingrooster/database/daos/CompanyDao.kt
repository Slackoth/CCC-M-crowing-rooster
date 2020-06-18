package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.Company

@Dao
interface CompanyDao {
    @Insert
    fun insertCompany(company: Company)
}