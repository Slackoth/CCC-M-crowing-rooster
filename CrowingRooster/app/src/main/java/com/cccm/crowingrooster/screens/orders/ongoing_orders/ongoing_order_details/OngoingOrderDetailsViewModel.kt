package com.cccm.crowingrooster.screens.orders.ongoing_orders.ongoing_order_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.entities.SaleDetails
import com.cccm.crowingrooster.database.entities.SaleMiniOrders
import com.cccm.crowingrooster.database.entities.order.OrderDetails
import com.cccm.crowingrooster.database.entities.order.OrderMiniOrder
import com.cccm.crowingrooster.network.repository.order.OrderDetailsRepository
import com.cccm.crowingrooster.network.repository.seller.SaleDetailsRepository

class OngoingOrderDetailsViewModel(
    private val orderDetailsRepository: OrderDetailsRepository,
    private val app: Application,
    private val code: String?,
    private val orderId: String?
): AndroidViewModel(app) {
    val orderDetails: LiveData<OrderDetails>
    val orderMiniOrder: LiveData<List<OrderMiniOrder>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
        orderDetails = orderDetailsRepository.getSpecific(code,orderId,"Pendiente")
        orderMiniOrder = orderDetailsRepository.getAll("Pendiente",orderId)
    }
}