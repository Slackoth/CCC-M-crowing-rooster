package com.cccm.crowingrooster.screens.sales.ongoing_sales

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.entities.SalePreview
import com.cccm.crowingrooster.network.repository.seller.SalePreviewRepository
import kotlinx.coroutines.*

class OngoingSalesViewModel(
    private val salePreviewRepository: SalePreviewRepository,
    app: Application
): AndroidViewModel(app) {
    private val viewModelJob: CompletableJob = Job()
    val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var salePreviews: LiveData<List<SalePreview>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
        salePreviews = salePreviewRepository.getAll("V-2020-0","Pendiente")
            .also { _isLoading.postValue(false) }
    }

    fun refresh() {
        //_isLoading.value = true
        //uiScope.launch(Dispatchers.IO) {
            salePreviews = salePreviewRepository.getAll("V-2020-0","Pendiente")
            //Thread.sleep(4000)
          //  _isLoading.postValue(false)
        //}

    }

}