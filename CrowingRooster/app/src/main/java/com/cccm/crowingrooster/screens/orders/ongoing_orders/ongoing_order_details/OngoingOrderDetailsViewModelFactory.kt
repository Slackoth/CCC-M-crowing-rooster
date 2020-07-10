package com.cccm.crowingrooster.screens.orders.ongoing_orders.ongoing_order_details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.order.OrderDetailsRepository
import com.cccm.crowingrooster.network.repository.seller.SaleDetailsRepository
import com.cccm.crowingrooster.screens.sales.ongoing_sales.ongoing_sale_details.OngoingSalesDetailsViewModel

class OngoingOrderDetailsViewModelFactory(
    private val orderDetailsRepository: OrderDetailsRepository,
    private val app: Application,
    private val code: String?,
    private val orderId: String?
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OngoingOrderDetailsViewModel::class.java)) {
            return OngoingOrderDetailsViewModel(orderDetailsRepository,app,code,orderId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}