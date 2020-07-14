package com.cccm.crowingrooster.screens.deliveries.ongoing_deliveries

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.entities.DeliveryPreview
import com.cccm.crowingrooster.network.repository.delivery.DeliveryPreviewRepository

class OngoingDeliveryViewModel(
    private val deliveryPreviewRepository: DeliveryPreviewRepository,
    app: Application,
    private val deliveryManCode: String?
): AndroidViewModel(app) {
    var deliveryPreview: LiveData<List<DeliveryPreview>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
        deliveryPreview = deliveryPreviewRepository.getAll(deliveryManCode!!, "Pendiente")
    }

    fun refresh() {
        deliveryPreview = deliveryPreviewRepository.getAll(deliveryManCode!!, "Pendiente")
    }
}