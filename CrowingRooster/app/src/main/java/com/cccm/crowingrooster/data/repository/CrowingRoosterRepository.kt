package com.cccm.crowingrooster.data.repository

import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.data.db.entities.SellerClient

interface CrowingRoosterRepository {

    /*TODO: SellerClient functions*/
    suspend fun getAllSellerClients(): List<SellerClient>

}