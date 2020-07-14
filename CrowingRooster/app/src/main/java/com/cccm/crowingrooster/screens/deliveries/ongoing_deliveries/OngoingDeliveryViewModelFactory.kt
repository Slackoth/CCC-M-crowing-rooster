package com.cccm.crowingrooster.screens.deliveries.ongoing_deliveries

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.delivery.DeliveryPreviewRepository
import com.cccm.crowingrooster.screens.deliveries.successful_deliveries.SuccessfulDeliveriesViewModel

class OngoingDeliveryViewModelFactory (
    private val deliveryPreviewRepository: DeliveryPreviewRepository,
    private val app: Application,
    private val deliveryManCode: String?
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OngoingDeliveryViewModel::class.java)) {
            return OngoingDeliveryViewModel(
                deliveryPreviewRepository,
                app,
                deliveryManCode
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}