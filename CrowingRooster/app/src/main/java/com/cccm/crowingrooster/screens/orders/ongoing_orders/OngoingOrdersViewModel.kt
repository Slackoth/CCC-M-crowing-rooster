package com.cccm.crowingrooster.screens.orders.ongoing_orders

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.entities.order.OrderPreview
import com.cccm.crowingrooster.network.repository.order.OrderPreviewRepository

class OngoingOrdersViewModel(
    private val orderPreviewRepository: OrderPreviewRepository,
    private val app: Application,
    private val buyerCode: String?
): AndroidViewModel(app) {
    var orderPreviews: LiveData<List<OrderPreview>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        //_isLoading.value = true
        orderPreviews = orderPreviewRepository.getAll(buyerCode,"Pendiente")
    }

    fun refresh() {
        orderPreviews = orderPreviewRepository.getAll(buyerCode,"Pendiente")
    }
}