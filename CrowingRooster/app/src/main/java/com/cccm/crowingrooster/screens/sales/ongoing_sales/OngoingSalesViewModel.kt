package com.cccm.crowingrooster.screens.sales.ongoing_sales

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.entities.SalePreview
import com.cccm.crowingrooster.network.repository.seller.SalePreviewRepository

class OngoingSalesViewModel(
    private val salePreviewRepository: SalePreviewRepository,
    app: Application
): AndroidViewModel(app) {

    val salePreviews: LiveData<List<SalePreview>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
        salePreviews = salePreviewRepository.getAll("V-00000000","Pendiente")
            .also { _isLoading.postValue(false) }
    }

}