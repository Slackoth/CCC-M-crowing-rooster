package com.cccm.crowingrooster.network.repository.delivery

import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.DeliveryPreviewDao
import com.cccm.crowingrooster.database.entities.DeliveryPreview
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class DeliveryPreviewRepository (private val deliveryPreviewDao: DeliveryPreviewDao) {
    fun getAll(code: String, state: String): LiveData<List<DeliveryPreview>> {
        refreshDeliveryPreview(code,state)
        return deliveryPreviewDao.getAll(code,state)
    }

    private fun refreshDeliveryPreview(code: String, state: String) {
        if (isFetchedNeeded(ZonedDateTime.now().minusHours(1))) {
            GlobalScope.launch {
                try {
                    val deliveryPreviews = when(state) {
                        "Exitosa" -> CrowingRoosterApiService.CrowingRoosterApi
                            .retrofitService.getSuccessfulDeliveryPreviewAsync(code)
                        else -> CrowingRoosterApiService.CrowingRoosterApi
                            .retrofitService.getOngoingDeliveryPreviewAsync(code)
                    }
                    Log.d("deliveryPrevs", "${deliveryPreviews}")

                    for (deliveryPreview in deliveryPreviews) {
                        Log.d("deliveryPrev", "${deliveryPreview}")
                        deliveryPreviewDao.insert(deliveryPreview)
                    }
                }
                catch (e: Exception) {
                    Log.d("Connection", "No connection: ${e.message}")
                }
            }
        }
    }

    private fun isFetchedNeeded(lastFetch: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetch.isBefore(thirtyMinAgo)
    }

    companion object {
        private var INSTANCE: DeliveryPreviewRepository? = null

        fun getInstance(deliveryPreviewDao: DeliveryPreviewDao) = INSTANCE ?: createInstance(deliveryPreviewDao)

        private fun createInstance(deliveryPreviewDao: DeliveryPreviewDao) = DeliveryPreviewRepository(deliveryPreviewDao)
    }
}