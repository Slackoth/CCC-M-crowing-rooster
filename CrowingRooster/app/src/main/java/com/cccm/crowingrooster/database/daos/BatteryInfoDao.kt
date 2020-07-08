package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.Battery
import com.cccm.crowingrooster.database.entities.BatteryInfo

interface BatteryInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(batteryInfo: BatteryInfo)

    @Query("SELECT * FROM battery WHERE id_bateria = :id")
    fun getBattery(id:Int): LiveData<BatteryInfo>
}