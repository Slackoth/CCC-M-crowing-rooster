package com.cccm.crowingrooster.screens.buyer_profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.order.BuyerProfileRepository
import com.cccm.crowingrooster.network.repository.seller.SellerRepository
import com.cccm.crowingrooster.screens.seller_profile.SellerProfileViewModel

class BuyerProfileViewModelFactory(
    private val buyerRepository: BuyerProfileRepository,
    private val app: Application,
    private val buyerCode: String?
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BuyerProfileViewModel::class.java)) {
            return BuyerProfileViewModel(buyerRepository,app,buyerCode) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}