package com.cccm.crowingrooster.data.network.data_source

import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.data.db.entities.SellerClient

interface CrowingRoosterNetworkDataSource {
    /*TODO: SellerClient LiveData*/
    val downloadedSellerClients: LiveData<List<SellerClient>>

    /*TODO: SellerClient functions*/
    suspend fun fetchSellerClients()

}