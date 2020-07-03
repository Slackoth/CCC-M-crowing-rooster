package com.cccm.crowingrooster.screens.seller_profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import com.cccm.crowingrooster.network.repository.seller.SellerRepository

class SellerProfileViewModel(
    sellerRepository: SellerRepository,
    app: Application
): AndroidViewModel(app) {
    val seller = sellerRepository.getSpecific("V-000000")
}