package com.cccm.crowingrooster.screens.deliveries.successful_deliveries.successful_delivery_details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.delivery.DeliveryDetailsRepository

class SuccessfulDeliveryDetailDialogViewModelFactory(
    private val deliveryDetailsRepository: DeliveryDetailsRepository,
    private val app: Application,
    private val deliveryManCode: String?,
    private val entregaId: Int?
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuccessfulDeliveryDetailDialogViewModel::class.java)) {
            return SuccessfulDeliveryDetailDialogViewModel(deliveryDetailsRepository,app,deliveryManCode,entregaId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}