package com.cccm.crowingrooster.screens.orders.successful_orders

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.order.OrderPreviewRepository
import com.cccm.crowingrooster.network.repository.seller.SalePreviewRepository
import com.cccm.crowingrooster.screens.sales.successful_sales.SuccessfulSalesViewModel

class SuccessfulOrdersViewModelFactory(
    private val orderPreviewRepository: OrderPreviewRepository,
    private val app: Application,
    private val buyerCode: String?
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuccessfulOrdersViewModel::class.java)) {
            return SuccessfulOrdersViewModel(orderPreviewRepository,app,buyerCode) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}