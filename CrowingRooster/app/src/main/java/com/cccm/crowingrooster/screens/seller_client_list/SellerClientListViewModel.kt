package com.cccm.crowingrooster.screens.seller_client_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.daos.SellerClientDao
import com.cccm.crowingrooster.database.entities.SellerClient
import com.cccm.crowingrooster.network.repository.CrowingRoosterRepository
import kotlinx.coroutines.*

class SellerClientListViewModel(
    private val crowingRoosterRepository: CrowingRoosterRepository,
    private val app: Application
): AndroidViewModel(app) {
    private val viewModelJob: CompletableJob = Job()
    val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _clients = MutableLiveData<List<SellerClient>>()
    val clients: LiveData<List<SellerClient>>
        get() = _clients


    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private fun getSellerClient() {
        _clients.value = listOf()
        uiScope.launch {
            val clientList = crowingRoosterRepository.getAllSellerClient()

            _clients.postValue(clientList.value)
        }
    }


//    suspend fun addBuyer() {
//        _isLoading.value = true
//        withContext(Dispatchers.IO) {
//            Thread.sleep(2000)
//            userSource.insertUser(User("B","B","Luis","B","Buyer"))
//            buyerSource.insertBuyer(Buyer("B","B","@gmail.com",1))
//            //_clients.postValue(buyerSource.getAll().value)
//            _isLoading.postValue(false)
//        }
//    }

}