package com.cccm.crowingrooster.screens.deliveries.successful_deliveries.successful_delivery_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.entities.DeliveryDetails
import com.cccm.crowingrooster.database.entities.DeliveryMiniOrders
import com.cccm.crowingrooster.network.repository.delivery.DeliveryDetailsRepository

class SuccessfulDeliveryDetailDialogViewModel (
    private val deliveryDetailsRepository: DeliveryDetailsRepository,
    private val app: Application,
    private val deliveryManCode: String?,
    private val entregaId: Int?
): AndroidViewModel(app) {

    val deliveryDetails: LiveData<DeliveryDetails>
    val miniOrders: LiveData<List<DeliveryMiniOrders>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
        miniOrders = deliveryDetailsRepository.getAll("Exitosa", entregaId)
        deliveryDetails = deliveryDetailsRepository.getSpecific(deliveryManCode,entregaId,"Exitosa")
    }
}