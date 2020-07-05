package com.cccm.crowingrooster.screens.seller_client_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.entities.SellerClient
import com.cccm.crowingrooster.network.repository.seller.SellerClientRepository
import kotlinx.coroutines.*

class SellerClientListViewModel(
    private val sellerClientRepository: SellerClientRepository,
    app: Application
): AndroidViewModel(app) {
    private val viewModelJob: CompletableJob = Job()
    val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //val clients: LiveData<List<SellerClient>> = sellerClientRepository.getAll().also { _isLoading.postValue(false) }
    //private val _clients: MutableLiveData<List<SellerClient>> = MutableLiveData()
    val clients: LiveData<List<SellerClient>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
        clients = sellerClientRepository.getAll().also { _isLoading.postValue(false) }
    }

}
