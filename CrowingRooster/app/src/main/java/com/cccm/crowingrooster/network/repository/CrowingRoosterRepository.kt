package com.cccm.crowingrooster.network.repository

import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.SellerClientDao
import com.cccm.crowingrooster.database.entities.SellerClient

interface CrowingRoosterRepository {
    suspend fun getAllSellerClient(): LiveData<List<SellerClient>>
}