package com.cccm.crowingrooster.screens.deliveries.ongoing_deliveries.ongoing_deliveries_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.entities.DeliveryDetails
import com.cccm.crowingrooster.database.entities.DeliveryMiniOrders
import com.cccm.crowingrooster.network.repository.delivery.DeliveryDetailsRepository

class OngoingDeliveryDetailsViewModel(
    private val deliveryDetailsRepository: DeliveryDetailsRepository,
    private val app: Application,
    private val deliveryManCode: String?,
    private val deliveryId: Int?
): AndroidViewModel(app) {

    val deliveryDetails: LiveData<DeliveryDetails>
    val miniOrders: LiveData<List<DeliveryMiniOrders>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
        miniOrders = deliveryDetailsRepository.getAll("Pendiente", deliveryId)
        deliveryDetails = deliveryDetailsRepository.getSpecific(deliveryManCode,deliveryId,"Pendiente")
    }

    fun confirmDelivery(code:String?,deliveryId: Int?) {
        deliveryDetailsRepository.confirmDelivery(code,deliveryId)
    }
}