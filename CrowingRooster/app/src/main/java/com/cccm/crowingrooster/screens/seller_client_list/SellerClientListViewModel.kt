package com.cccm.crowingrooster.screens.seller_client_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.daos.BuyerDao
import com.cccm.crowingrooster.database.daos.UserDao
import com.cccm.crowingrooster.database.entities.Buyer
import com.cccm.crowingrooster.database.entities.User
import kotlinx.coroutines.*

class SellerClientListViewModel(
    private val buyerSource: BuyerDao,
    private val userSource: UserDao,
    private val app: Application
): AndroidViewModel(app) {
    private val viewModelJob: CompletableJob = Job()
    val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val clients: LiveData<MutableList<Buyer>> = buyerSource.getAll()

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    suspend fun addBuyer() {
        _isLoading.value = true
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            userSource.insertUser(User("B","B","Luis","B","Buyer"))
            buyerSource.insertBuyer(Buyer("B","B","@gmail.com",1))
            //_clients.postValue(buyerSource.getAll().value)
            _isLoading.postValue(false)
        }
    }

}