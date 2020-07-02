package com.cccm.crowingrooster.screens.seller_client_list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.data.repository.CrowingRoosterRepository

class SellerClientViewModelFactory(
    private val sellerClientRepository: CrowingRoosterRepository,
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SellerClientListViewModel::class.java)) {
            return SellerClientListViewModel(sellerClientRepository,app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}