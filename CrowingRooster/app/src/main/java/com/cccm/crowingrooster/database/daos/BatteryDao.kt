package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.Battery

@Dao
interface BatteryDao {
    @Insert
    fun insertBattery(battery: Battery)
}