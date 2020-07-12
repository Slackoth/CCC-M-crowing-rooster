package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.Battery

@Dao
interface BatteryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(battery: Battery)

    @Query("SELECT * FROM battery WHERE id_bateria = :id_bateria")
    fun getBattery(id_bateria:Int?): LiveData<Battery>

}