package com.cccm.crowingrooster.screens.sales.successful_sales

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.seller.SalePreviewRepository

class SuccessfulSalesViewModelFactory(
    private val salePreviewRepository: SalePreviewRepository,
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuccessfulSalesViewModel::class.java)) {
            return SuccessfulSalesViewModel(salePreviewRepository,app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}