package com.cccm.crowingrooster.network.repository.seller

import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.SellerClientDao
import com.cccm.crowingrooster.database.entities.SellerClient
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class SellerClientRepository (private val sellerClientDao: SellerClientDao) {
    fun getAll(code: String): LiveData<List<SellerClient>> {
        refreshSellerClient(code)
        return sellerClientDao.getAll()
    }

    private fun refreshSellerClient(code: String) {
        GlobalScope.launch {
            if (isFetchedNeeded(ZonedDateTime.now().minusHours(1))) {
                try {
                    val clients = CrowingRoosterApiService.CrowingRoosterApi
                        .retrofitService.getAllSellerClientAsync(code)
                    for (client in clients) {
                        sellerClientDao.insert(client)
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
        private var INSTANCE: SellerClientRepository? = null

        fun getInstance(
            sellerClientDao: SellerClientDao
        ) = INSTANCE
            ?: createInstance(
                sellerClientDao
            ).also {
                INSTANCE = it
            }

        private fun createInstance(
            sellerClientDao: SellerClientDao
        ) =
            SellerClientRepository(
                sellerClientDao
            )
    }
}