package com.cccm.crowingrooster.screens.sales.ongoing_sales

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.seller.SalePreviewRepository
import com.cccm.crowingrooster.network.repository.seller.SellerClientRepository
import com.cccm.crowingrooster.screens.seller_client_list.SellerClientListViewModel

class OngoingSalesViewModelFactory(
    private val salePreviewRepository: SalePreviewRepository,
    private val app: Application,
    private val sellerCode: String?
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OngoingSalesViewModel::class.java)) {
            return OngoingSalesViewModel(salePreviewRepository,app,sellerCode) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}