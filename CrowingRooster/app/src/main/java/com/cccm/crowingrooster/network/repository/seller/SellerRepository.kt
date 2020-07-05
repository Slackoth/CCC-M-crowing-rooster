package com.cccm.crowingrooster.network.repository.seller

import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.SellerDao
import com.cccm.crowingrooster.database.entities.Seller
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class SellerRepository(private val sellerDao: SellerDao) {

    fun getSpecific(code: String): LiveData<Seller> {
        refreshSeller(code)
        return sellerDao.getSeller(code)
    }

    private fun refreshSeller(code: String) {
        if (isFetchedNeeded(ZonedDateTime.now().minusHours(1))) {
            GlobalScope.launch {
                try {
                    val sellers = CrowingRoosterApiService.CrowingRoosterApi
                        .retrofitService.getSellerAsync(code)
                    for (seller in sellers) {
                        sellerDao.insert(seller)
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
        private var INSTANCE: SellerRepository? = null

        fun getInstance(sellerDao: SellerDao) = INSTANCE ?: createInstance(sellerDao)
            .also { INSTANCE = it }

        private fun createInstance(sellerDao: SellerDao) = SellerRepository(sellerDao)
    }
}