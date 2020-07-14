package com.cccm.crowingrooster.network.repository.delivery

import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.DeliveryDetailsDao
import com.cccm.crowingrooster.database.daos.DeliveryMiniOrdersDao
import com.cccm.crowingrooster.database.entities.DeliveryDetails
import com.cccm.crowingrooster.database.entities.DeliveryMiniOrders
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class DeliveryDetailsRepository (
    private val deliveryDetailsDao: DeliveryDetailsDao,
    private val deliveryMiniOrdersDao: DeliveryMiniOrdersDao
) {
    fun getSpecific(deliveryManCode: String?, entregaId: Int?, state: String): LiveData<DeliveryDetails> {
        refreshSuccessfulDeliveryDetails(deliveryManCode,entregaId,state)
        return deliveryDetailsDao.getSpecific(deliveryManCode)
    }

    fun getAll(state: String?, entregaId: Int?): LiveData<List<DeliveryMiniOrders>> {
        return deliveryMiniOrdersDao.getAll(state,entregaId)
    }

    fun confirmDelivery(code:String?,deliveryId:Int?) {
        GlobalScope.launch {
            try {
                CrowingRoosterApiService.CrowingRoosterApi
                    .retrofitService.confirmDeliveryAsync(code,deliveryId)
            }
            catch (e: Exception) {
                Log.d("Connectionx","No connection: ${e.message}")
            }
        }
    }

    private  fun refreshSuccessfulDeliveryDetails(deliveryManCode: String?, entregaId: Int?, state: String) {
        if (isFetchedNeeded(ZonedDateTime.now().minusHours(1))) {
            GlobalScope.launch {
                try {
                    val deliveryDetails = when(state) {
                        "Exitosa" -> CrowingRoosterApiService.CrowingRoosterApi
                            .retrofitService.getSuccessfulDeliveryDetailsAsync(deliveryManCode, entregaId)
                        else -> CrowingRoosterApiService.CrowingRoosterApi
                            .retrofitService.getOngoingDeliveryDetailsAsync(deliveryManCode, entregaId)
                    }
                    Log.d("deliveryDetails" , "$deliveryDetails")
                    Log.d("deliveryDetailsMini", "${deliveryDetails[0].minidelivery}")

                    for (delivery in deliveryDetails) {
                        Log.d("delivery", "$delivery")
                        deliveryDetailsDao.insert(delivery)
                    }
                    for (miniOrder in deliveryDetails[0].minidelivery) {
                        Log.d("miniOrderDel", "$miniOrder")
                        deliveryMiniOrdersDao.insert(miniOrder)
                    }
                }
                catch (e: Exception) {
                    Log.d("Connection", "No Connection: ${e.message}")
                }
            }
        }
    }

    private fun isFetchedNeeded(lastFetch: org.threeten.bp.ZonedDateTime): Boolean {
        val thirtyMinAgo = org.threeten.bp.ZonedDateTime.now().minusMinutes(30)
        return lastFetch.isBefore(thirtyMinAgo)
    }

    companion object {
        private var INSTANCE: DeliveryDetailsRepository? = null

        fun getInstance(
            deliveryDetailsDao: DeliveryDetailsDao,
            deliveryMiniOrdersDao: DeliveryMiniOrdersDao
        ) = INSTANCE ?: createInstance(deliveryDetailsDao, deliveryMiniOrdersDao)
            .also { INSTANCE = it}

        private fun createInstance(
            deliveryDetailsDao: DeliveryDetailsDao,
            deliveryMiniOrdersDao: DeliveryMiniOrdersDao
        ) = DeliveryDetailsRepository(deliveryDetailsDao, deliveryMiniOrdersDao)
    }
}