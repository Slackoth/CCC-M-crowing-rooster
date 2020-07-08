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
    private val app: Application
): AndroidViewModel(app) {
    val saleDetails: LiveData<com.cccm.crowingrooster.database.entities.SaleDetails>
    val miniOrders: LiveData<List<SaleMiniOrders>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
        miniOrders = saleDetailsRepository.getAll("Pendiente","VT-2020-3")
        saleDetails = saleDetailsRepository.getSpecific("V-2020-0","O-2020-1","Pendiente")
    }
}