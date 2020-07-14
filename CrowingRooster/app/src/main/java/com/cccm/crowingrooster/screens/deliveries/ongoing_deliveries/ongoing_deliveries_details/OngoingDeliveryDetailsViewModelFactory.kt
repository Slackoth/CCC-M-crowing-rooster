package com.cccm.crowingrooster.screens.deliveries.ongoing_deliveries.ongoing_deliveries_details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.delivery.DeliveryDetailsRepository
import com.cccm.crowingrooster.screens.deliveries.successful_deliveries.successful_delivery_details.SuccessfulDeliveryDetailDialogViewModel

class OngoingDeliveryDetailsViewModelFactory(
    private val deliveryDetailsRepository: DeliveryDetailsRepository,
    private val app: Application,
    private val deliveryManCode: String?,
    private val deliveryId: Int?
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OngoingDeliveryDetailsViewModel::class.java)) {
            return OngoingDeliveryDetailsViewModel(deliveryDetailsRepository,app,deliveryManCode,deliveryId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}