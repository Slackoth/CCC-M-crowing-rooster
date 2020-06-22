package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.Phone

@Dao
interface PhoneDao {
    @Insert
    fun insertPhone(phone: Phone)
}