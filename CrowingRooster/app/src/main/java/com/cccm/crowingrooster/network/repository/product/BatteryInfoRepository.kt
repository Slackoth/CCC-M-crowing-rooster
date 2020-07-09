package com.cccm.crowingrooster.network.repository.product

import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.BatteryDao
import com.cccm.crowingrooster.database.daos.BatteryInfoDao
import com.cccm.crowingrooster.database.entities.Battery
import com.cccm.crowingrooster.database.entities.BatteryInfo
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class BatteryInfoRepository (val BatteryInfoDao: BatteryInfoDao){

    fun getSpecific(modelo: String): LiveData<BatteryInfo> {
        refreshBattery(modelo)
        return BatteryInfoDao.getBattery(modelo)
    }
    private fun refreshBattery(model: String) {
        Log.d("Caca2", "Batteries.toString()")
        if (isFetchedNeeded(ZonedDateTime.now().minusHours(1))) {
            Log.d("Caca2", "*x2")
            GlobalScope.launch {
                Log.d("Caca2", "suputamadre")
                try {
                    Log.d("Caca2", "Suputamadre*2")
                    val Batteries = CrowingRoosterApiService.CrowingRoosterApi
                        .retrofitService.getProductInfoAsync(model)
                    Log.d("Caca2", Batteries.toString())

                    for (Battery in Batteries) {
                        Log.d("Caca2", "pipomelachupas")
                        BatteryInfoDao.insert(Battery)
                    }
                    Log.d("Bttry", "${Batteries[0].direccion}")
                } catch (e: Exception) {
                    Log.d("Connection", "No connection: ${e.message}")
                }
            }
        }else{
            Log.d("Caca", "Cacth la log")
        }
    }

    private fun isFetchedNeeded(lastFetch: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetch.isBefore(thirtyMinAgo)
    }


    companion object {
        private var INSTANCE: BatteryInfoRepository? = null

        fun getInstance(
            BatteryInfoDao: BatteryInfoDao
        ) = INSTANCE
            ?: createInstance(
                BatteryInfoDao
            ).also {
                INSTANCE = it
            }

        private fun createInstance(
            BatteryInfoDao: BatteryInfoDao
        ) =
            BatteryInfoRepository(
                BatteryInfoDao
            )
    }


}