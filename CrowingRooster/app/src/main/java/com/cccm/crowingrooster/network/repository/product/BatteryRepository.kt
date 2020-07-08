package com.cccm.crowingrooster.network.repository.product

import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.BatteryDao
import com.cccm.crowingrooster.database.daos.SellerClientDao
import com.cccm.crowingrooster.database.entities.Battery
import com.cccm.crowingrooster.database.entities.Seller
import com.cccm.crowingrooster.database.entities.SellerClient
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.Product
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import com.cccm.crowingrooster.network.repository.seller.SellerClientRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class BatteryRepository (private val BatteryDao: BatteryDao){
//    fun getAll(): LiveData<List<Product>> {
//        //codigo, conectar a api
//
//    }
//
fun getSpecific(id: Int): LiveData<Battery> {
    refreshBattery(id)
    return BatteryDao.getBattery(id)
}


    private fun refreshBattery(id: Int) {
        Log.d("Caca", "Batteries.toString()")
        if (isFetchedNeeded(ZonedDateTime.now().minusHours(1))) {
            Log.d("Caca", "*x2")
            GlobalScope.launch {
                Log.d("Caca", "suputamadre")
                try {
                    Log.d("Caca", "Suputamadre*2")
                    val Batteries = CrowingRoosterApiService.CrowingRoosterApi
                        .retrofitService.getProductAsync(id)
                    Log.d("Caca", Batteries.toString())
                    for (Battery in Batteries) {
                        Log.d("Caca", "pipomelachupas")
                        BatteryDao.insert(Battery)
                    }
                    Log.d("Bttry", "${Batteries[0].modelo}")
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
        private var INSTANCE: BatteryRepository? = null

        fun getInstance(
            BatteryDao: BatteryDao
        ) = INSTANCE
            ?: createInstance(
                BatteryDao
            ).also {
                INSTANCE = it
            }

        private fun createInstance(
            BatteryDao: BatteryDao
        ) =
            BatteryRepository(
                BatteryDao
            )
    }
}