package com.cccm.crowingrooster.data.network.data_source

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.data.CrowingRoosterApiService
import com.cccm.crowingrooster.data.db.entities.SellerClient
import java.lang.Exception

class CrowingRoosterNetworkDataSourceImpl : CrowingRoosterNetworkDataSource {
    private val _downloadedSellerClients = MutableLiveData<List<SellerClient>>()

    override val downloadedSellerClients: LiveData<List<SellerClient>>
        get() = _downloadedSellerClients

    @SuppressLint("LogNotTimber")
    override suspend fun fetchSellerClients() {
        try {
            val fetchedClients = CrowingRoosterApiService.CrowingRoosterApi
                .retrofitService.getAllSellerClientsAsync()

            _downloadedSellerClients.value = fetchedClients
        }
        catch (e: Exception) {
            Log.d("Connection","No connection: ${e.message}")
        }
    }

    companion object {
        private var INSTANCE: CrowingRoosterNetworkDataSourceImpl? = null

        fun getInstance() = INSTANCE ?: createInstance().also {
            INSTANCE = it
        }

        private fun createInstance() = CrowingRoosterNetworkDataSourceImpl()
    }
}