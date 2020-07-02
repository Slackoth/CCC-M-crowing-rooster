package com.cccm.crowingrooster.screens.seller_client_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.data.db.entities.SellerClient
import com.cccm.crowingrooster.data.repository.CrowingRoosterRepository
//import com.cccm.crowingrooster.database.entities.Buyer
import kotlinx.coroutines.*

class SellerClientListViewModel(
    private val sellerClientRepository: CrowingRoosterRepository,
    private val app: Application
): AndroidViewModel(app) {
    private val viewModelJob: CompletableJob = Job()
    private val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _clients = MutableLiveData<List<SellerClient>>()
    val clients
        get() = _clients

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        getClients()
    }

    private fun getClients() {
        _isLoading.value = true
        _clients.value = mutableListOf()
        uiScope.launch {
            val getClients = sellerClientRepository.getAllSellerClients()

            _clients.postValue(getClients)
            _isLoading.postValue(false)
        }
    }

}