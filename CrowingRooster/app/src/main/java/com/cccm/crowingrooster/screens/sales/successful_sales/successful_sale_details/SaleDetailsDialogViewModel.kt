package com.cccm.crowingrooster.screens.sales.successful_sales.successful_sale_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.entities.SaleDetails
import com.cccm.crowingrooster.database.entities.SaleMiniOrders
//import com.cccm.crowingrooster.database.entities.SuccessfulSaleDetailsAndSaleMiniOrders
import com.cccm.crowingrooster.network.repository.seller.SaleDetailsRepository

class SaleDetailsDialogViewModel(
    private val saleDetailsRepository: SaleDetailsRepository,
    private val app: Application
): AndroidViewModel(app) {

    val saleDetails: LiveData<SaleDetails>
    val miniOrders: LiveData<List<SaleMiniOrders>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
        miniOrders = saleDetailsRepository.getAll("Exitosa")
        saleDetails = saleDetailsRepository.getSpecific("V-2020-0","O-2020-0","Exitosa")
    }

}