package com.cccm.crowingrooster.network.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.daos.SellerClientDao
import com.cccm.crowingrooster.database.entities.SellerClient
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime
import java.lang.Exception

class CrowingRoosterRepositoryImpl(
    private val sellerClientDao: SellerClientDao
) : CrowingRoosterRepository {
    override suspend fun getAllSellerClient(): LiveData<List<SellerClient>> {
        refreshSellerClient()
        return sellerClientDao.getAll()
    }

    private suspend fun refreshSellerClient() {
        if (isFetchedNeeded(ZonedDateTime.now().minusHours(1))) {
            try {
                val fetch = CrowingRoosterApiService.CrowingRoosterApi
                    .retrofitService.getAllSellerClientAsync()
                for (client in fetch) {
                    sellerClientDao.insert(client)
                }
            }
            catch (e: Exception) {
                Log.d("Connection","No connection: ${e.message}")
            }
        }
    }

    private fun isFetchedNeeded(lastFetch: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetch.isBefore(thirtyMinAgo)
    }

    companion object {
        private var INSTANCE: CrowingRoosterRepositoryImpl? = null

        fun getInstance(
            sellerClientDao: SellerClientDao
        ) = INSTANCE ?: createInstance(sellerClientDao).also {
            INSTANCE = it
        }

        private fun createInstance(
            sellerClientDao: SellerClientDao
        ) = CrowingRoosterRepositoryImpl(sellerClientDao)
    }

}