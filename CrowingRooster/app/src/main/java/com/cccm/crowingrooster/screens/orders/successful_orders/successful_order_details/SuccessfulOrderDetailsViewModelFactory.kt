package com.cccm.crowingrooster.screens.orders.successful_orders.successful_order_details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.order.OrderDetailsRepository
import com.cccm.crowingrooster.network.repository.seller.SaleDetailsRepository
import com.cccm.crowingrooster.screens.sales.successful_sales.successful_sale_details.SaleDetailsDialogViewModel

class SuccessfulOrderDetailsViewModelFactory(
    private val orderDetailsRepository: OrderDetailsRepository,
    private val app: Application,
    private val code: String?,
    private val orderId: String?
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuccessfulOrderDetailsViewModel::class.java)) {
            return SuccessfulOrderDetailsViewModel(orderDetailsRepository,app,code,orderId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}