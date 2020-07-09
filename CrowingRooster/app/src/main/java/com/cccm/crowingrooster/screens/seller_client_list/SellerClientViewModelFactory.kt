package com.cccm.crowingrooster.screens.seller_client_list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.login.LogInRepository
import com.cccm.crowingrooster.network.repository.seller.SellerClientRepository
import com.cccm.crowingrooster.screens.login.LogInViewModelFactory

class SellerClientViewModelFactory(
    private val sellerClientRepository: SellerClientRepository,
    private val app: Application,
    private val sellerCode: String?
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SellerClientListViewModel::class.java)) {
            return SellerClientListViewModel(sellerClientRepository,app,sellerCode) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}