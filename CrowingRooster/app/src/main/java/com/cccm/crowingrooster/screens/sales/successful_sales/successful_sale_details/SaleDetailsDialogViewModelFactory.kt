package com.cccm.crowingrooster.screens.sales.successful_sales.successful_sale_details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.seller.SaleDetailsRepository

class SaleDetailsDialogViewModelFactory(
    private val saleDetailsRepository: SaleDetailsRepository,
    private val app: Application,
    private val code: String?,
    private val orderId: String?,
    private val saleId: String?
    ): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SaleDetailsDialogViewModel::class.java)) {
            return SaleDetailsDialogViewModel(saleDetailsRepository,app,code,orderId,saleId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}