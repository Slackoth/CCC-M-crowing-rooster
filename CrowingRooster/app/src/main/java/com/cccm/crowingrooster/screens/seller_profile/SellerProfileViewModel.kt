package com.cccm.crowingrooster.screens.seller_profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import com.cccm.crowingrooster.network.repository.seller.SellerRepository

class SellerProfileViewModel(
    private val sellerRepository: SellerRepository,
    app: Application,
    private val sellerCode: String?
): AndroidViewModel(app) {
    val seller = sellerRepository.getSpecific(sellerCode)
}