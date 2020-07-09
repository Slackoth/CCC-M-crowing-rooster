package com.cccm.crowingrooster.network.repository.order

import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.SalePreviewDao
import com.cccm.crowingrooster.database.daos.order.OrderPreviewDao
import com.cccm.crowingrooster.database.entities.SalePreview
import com.cccm.crowingrooster.database.entities.order.OrderPreview
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import com.cccm.crowingrooster.network.repository.seller.SalePreviewRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class OrderPreviewRepository(private val orderPreviewDao: OrderPreviewDao) {
    fun getAll(code: String?, state: String): LiveData<List<OrderPreview>> {
        refreshOrderPreview(code,state)
        return orderPreviewDao.getAll(state)
    }

    private fun refreshOrderPreview(code: String?,state: String) {
        if (isFetchedNeeded(ZonedDateTime.now().minusHours(1))) {
            GlobalScope.launch {
                try {
                    val orderPreviews = when(state) {
                        "Exitosa" -> CrowingRoosterApiService.CrowingRoosterApi
                            .retrofitService.getSuccessfulOrderPreviewAsync(code)
                        "Pendiente" -> CrowingRoosterApiService.CrowingRoosterApi
                            .retrofitService.getOngoingOrderPreviewAsync(code)
                        else -> CrowingRoosterApiService.CrowingRoosterApi
                            .retrofitService.getCanceledOrderPreviewAsync(code)
                    }

                    for (orderPreview in orderPreviews) {
                        orderPreviewDao.insert(orderPreview)
                    }
                }
                catch (e: Exception) {
                    Log.d("Connection","No connection: ${e.message}")
                }
            }
        }
    }

    private fun isFetchedNeeded(lastFetch: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetch.isBefore(thirtyMinAgo)
    }


    companion object {
        private var INSTANCE: OrderPreviewRepository? = null

        fun getInstance(orderPreviewDao: OrderPreviewDao) = INSTANCE ?: createInstance(orderPreviewDao)
            .also { INSTANCE = it }

        private fun createInstance(orderPreviewDao: OrderPreviewDao) = OrderPreviewRepository(orderPreviewDao)
    }
}