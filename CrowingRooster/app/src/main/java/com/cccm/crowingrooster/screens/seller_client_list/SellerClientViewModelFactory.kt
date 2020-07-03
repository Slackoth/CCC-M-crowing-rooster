package com.cccm.crowingrooster.screens.seller_client_list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.database.daos.SellerClientDao
import com.cccm.crowingrooster.network.repository.CrowingRoosterRepository
import com.cccm.crowingrooster.network.repository.seller.SellerClientRepository

class SellerClientViewModelFactory(
    private val sellerClientRepository: SellerClientRepository,
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SellerClientListViewModel::class.java)) {
            return SellerClientListViewModel(sellerClientRepository,app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}