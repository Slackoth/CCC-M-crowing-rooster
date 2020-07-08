package com.cccm.crowingrooster.network.repository.seller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.daos.SalePreviewDao
import com.cccm.crowingrooster.database.daos.SellerDao
import com.cccm.crowingrooster.database.entities.SalePreview
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class SalePreviewRepository(private val salePreviewDao: SalePreviewDao) {


    fun getAll(code: String, state: String): LiveData<List<SalePreview>> {
        refreshSalePreview(code,state)
        return salePreviewDao.getAll(state)
    }

    private fun refreshSalePreview(code: String,state: String) {
        if (isFetchedNeeded(ZonedDateTime.now().minusHours(1))) {
            GlobalScope.launch {
                try {
                    val salePreviews = when(state) {
                        "Exitosa" -> CrowingRoosterApiService.CrowingRoosterApi
                            .retrofitService.getSuccessfulSalePreviewAsync(code)
                        else -> CrowingRoosterApiService.CrowingRoosterApi
                            .retrofitService.getOngoingSalePreviewAsync(code)
                    }

                    for (salePreview in salePreviews) {
                        salePreviewDao.insert(salePreview)
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
        private var INSTANCE: SalePreviewRepository? = null

        fun getInstance(salePreviewDao: SalePreviewDao) = INSTANCE ?: createInstance(salePreviewDao)
            .also { INSTANCE = it }

        private fun createInstance(salePreviewDao: SalePreviewDao) = SalePreviewRepository(salePreviewDao)
    }
}