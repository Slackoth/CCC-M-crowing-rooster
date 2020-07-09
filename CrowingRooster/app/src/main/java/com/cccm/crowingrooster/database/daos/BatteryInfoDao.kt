package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.Battery
import com.cccm.crowingrooster.database.entities.BatteryInfo

@Dao
interface BatteryInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(batteryInfo: BatteryInfo)

    @Query("SELECT * FROM product_info WHERE modelo = :modelo")
    fun getBattery(modelo:String): LiveData<BatteryInfo>



}