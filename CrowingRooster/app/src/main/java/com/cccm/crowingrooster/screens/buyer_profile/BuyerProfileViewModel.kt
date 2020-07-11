package com.cccm.crowingrooster.screens.buyer_profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.database.entities.order.Buyer
import com.cccm.crowingrooster.network.repository.order.BuyerProfileRepository
import com.cccm.crowingrooster.network.repository.seller.SellerRepository
import com.cccm.crowingrooster.screens.seller_profile.SellerProfileViewModel

class BuyerProfileViewModel(
    private val buyerRepository: BuyerProfileRepository,
    private val app: Application,
    private val buyerCode: String?
): AndroidViewModel(app) {
    var buyer: LiveData<Buyer> = buyerRepository.getSpecific(buyerCode)
}