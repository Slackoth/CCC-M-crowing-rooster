package com.cccm.crowingrooster.screens.deliveries.successful_deliveries

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.delivery.DeliveryPreviewRepository
import kotlin.IllegalArgumentException

class SuccessfulDeliveriesViewModelFactory (
    private val deliveryPreviewRepository: DeliveryPreviewRepository,
    private val app: Application,
    private val deliveryManCode: String?
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuccessfulDeliveriesViewModel::class.java)) {
            return SuccessfulDeliveriesViewModel(
                deliveryPreviewRepository,
                app,
                deliveryManCode
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}