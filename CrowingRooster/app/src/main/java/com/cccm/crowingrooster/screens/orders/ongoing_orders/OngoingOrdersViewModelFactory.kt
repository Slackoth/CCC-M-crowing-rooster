package com.cccm.crowingrooster.screens.orders.ongoing_orders

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.order.OrderPreviewRepository
import com.cccm.crowingrooster.screens.orders.successful_orders.SuccessfulOrdersViewModel

class OngoingOrdersViewModelFactory(
    private val orderPreviewRepository: OrderPreviewRepository,
    private val app: Application,
    private val buyerCode: String?
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OngoingOrdersViewModel::class.java)) {
            return OngoingOrdersViewModel(orderPreviewRepository,app,buyerCode) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}