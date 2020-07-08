package com.cccm.crowingrooster.screens.sales.ongoing_sales.ongoing_sale_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cccm.crowingrooster.database.entities.SaleMiniOrders
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.SaleDetails
import com.cccm.crowingrooster.network.repository.seller.SaleDetailsRepository

class OngoingSalesDetailsViewModel(
    private val saleDetailsRepository: SaleDetailsRepository,
    private val app: Application,
    private val code: String?,
    private val orderId: String?,
    private val saleId: String?
): AndroidViewModel(app) {
    val saleDetails: LiveData<com.cccm.crowingrooster.database.entities.SaleDetails>
    val miniOrders: LiveData<List<SaleMiniOrders>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
        miniOrders = saleDetailsRepository.getAll("Pendiente",saleId)
        saleDetails = saleDetailsRepository.getSpecific(code,orderId,"Pendiente")
    }
}