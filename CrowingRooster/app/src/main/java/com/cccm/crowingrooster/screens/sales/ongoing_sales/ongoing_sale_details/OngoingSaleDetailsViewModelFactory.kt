package com.cccm.crowingrooster.screens.sales.ongoing_sales.ongoing_sale_details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.seller.SaleDetailsRepository
import com.cccm.crowingrooster.screens.sales.successful_sales.successful_sale_details.SaleDetailsDialogViewModel

class OngoingSaleDetailsViewModelFactory(
    private val saleDetailsRepository: SaleDetailsRepository,
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OngoingSalesDetailsViewModel::class.java)) {
            return OngoingSalesDetailsViewModel(saleDetailsRepository,app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}