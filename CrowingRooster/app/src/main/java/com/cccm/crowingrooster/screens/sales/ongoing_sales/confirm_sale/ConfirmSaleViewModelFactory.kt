package com.cccm.crowingrooster.screens.sales.ongoing_sales.confirm_sale

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.seller.ConfirmSaleRepository
import com.cccm.crowingrooster.network.repository.seller.SaleDetailsRepository
import com.cccm.crowingrooster.screens.sales.ongoing_sales.ongoing_sale_details.OngoingSalesDetailsViewModel

class ConfirmSaleViewModelFactory(
    private val confirmSaleRepository: ConfirmSaleRepository,
    private val saleId: String?,
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConfirmSaleViewModel::class.java)) {
            return ConfirmSaleViewModel(confirmSaleRepository,saleId,app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}