package com.cccm.crowingrooster.screens.seller_profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.seller.SellerRepository
import com.cccm.crowingrooster.screens.seller_client_list.SellerClientListViewModel

class SellerProfileViewModelFactory(

    private val sellerRepository: SellerRepository,
    private val app: Application,
    private val sellerCode: String?

): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SellerProfileViewModel::class.java)) {
            return SellerProfileViewModel(sellerRepository,app,sellerCode) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}