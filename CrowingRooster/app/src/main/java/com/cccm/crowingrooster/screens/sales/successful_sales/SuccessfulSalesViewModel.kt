package com.cccm.crowingrooster.screens.sales.successful_sales

import android.annotation.SuppressLint
import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cccm.crowingrooster.database.entities.SalePreview
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.Sale
import com.cccm.crowingrooster.network.repository.seller.SalePreviewRepository

class SuccessfulSalesViewModel(
    private val salePreviewRepository: SalePreviewRepository,
    app: Application
): AndroidViewModel(app) {
    var salePreviews: LiveData<List<SalePreview>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
        salePreviews = salePreviewRepository.getAll("V-2020-0","Exitosa")
            .also { _isLoading.postValue(false) }
    }

    fun refresh() {
        salePreviews = salePreviewRepository.getAll("V-2020-0","Exitosa")
    }
}