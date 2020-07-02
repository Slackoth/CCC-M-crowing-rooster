package com.cccm.crowingrooster.data.network.data_source

import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.data.db.entities.SellerClient

interface CrowingRoosterNetworkDataSource {
    val downloadedSellerClients: LiveData<List<SellerClient>>

    suspend fun fetchSellerClients()

}