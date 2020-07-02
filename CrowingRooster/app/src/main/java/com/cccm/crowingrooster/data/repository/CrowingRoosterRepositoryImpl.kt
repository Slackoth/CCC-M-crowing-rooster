package com.cccm.crowingrooster.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.data.db.entities.SellerClient
import com.cccm.crowingrooster.data.db.daos.SellerClientDao
import com.cccm.crowingrooster.data.network.data_source.CrowingRoosterNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class CrowingRoosterRepositoryImpl(
    private val sellerClientDao: SellerClientDao,
    private val crowingRoosterNetworkDataSource: CrowingRoosterNetworkDataSource
) : CrowingRoosterRepository {

    init {
        crowingRoosterNetworkDataSource.downloadedSellerClients.observeForever {
            persistFetchedSellerClients(it)
        }
    }

    override suspend fun getAllSellerClients(): List<SellerClient> {
        initSellerClientData()
        return withContext(Dispatchers.IO) {
            return@withContext sellerClientDao.getAllSellerClients()
        }
    }

    private fun persistFetchedSellerClients(fetchedSellerClients: List<SellerClient>) {
        GlobalScope.launch(Dispatchers.IO) {
            for (client in fetchedSellerClients) {
                sellerClientDao.insert(client)
            }
        }
    }

    private suspend fun fetchSellerClients() {
        crowingRoosterNetworkDataSource.fetchSellerClients()
    }

    private suspend fun initSellerClientData() {
        if (isFetchedNeeded(ZonedDateTime.now().minusHours(1)))
            fetchSellerClients()
    }

    private fun isFetchedNeeded(lastFetch: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetch.isBefore(thirtyMinAgo)
    }

    companion object {
        private var INSTANCE: CrowingRoosterRepositoryImpl? = null

        fun getInstance(
            sellerClientDao: SellerClientDao,
            crowingRoosterNetworkDataSource: CrowingRoosterNetworkDataSource
        ) = INSTANCE ?: createInstance(sellerClientDao,crowingRoosterNetworkDataSource).also {
            INSTANCE = it
        }

        private fun createInstance(
            sellerClientDao: SellerClientDao,
            crowingRoosterNetworkDataSource: CrowingRoosterNetworkDataSource
        ) = CrowingRoosterRepositoryImpl(sellerClientDao,crowingRoosterNetworkDataSource)
    }


}