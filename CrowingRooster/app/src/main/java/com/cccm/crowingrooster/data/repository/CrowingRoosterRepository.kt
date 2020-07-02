package com.cccm.crowingrooster.data.repository

import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.data.db.entities.SellerClient

interface CrowingRoosterRepository {
    suspend fun getAllSellerClients(): List<SellerClient>

}