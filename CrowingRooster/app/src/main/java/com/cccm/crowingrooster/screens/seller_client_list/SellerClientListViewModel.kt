package com.cccm.crowingrooster.screens.seller_client_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cccm.crowingrooster.data.repository.KantoPokemonRepository
import com.cccm.crowingrooster.database.daos.BuyerDao
import com.cccm.crowingrooster.database.daos.UserDao
import com.cccm.crowingrooster.database.entities.Buyer
import com.cccm.crowingrooster.database.entities.User
import com.cccm.crowingrooster.internal.UnitPokemon
import com.cccm.crowingrooster.internal.lazyDeferred
import kotlinx.coroutines.*

class SellerClientListViewModel(
    /*private val buyerSource: BuyerDao,
    private val userSource: UserDao,*/
    private val kantoPokemonRepository: KantoPokemonRepository/*,
    private val app: Application*/
): ViewModel()/*AndroidViewModel(app)*/ {
    private val unitPokemon = UnitPokemon.BULBASAUR
    val isBulbasaur: Boolean
        get() = unitPokemon == UnitPokemon.BULBASAUR

    /*TODO: Lazy -> It would not be initialized instantly as the ViewModel is instantiated
    *  rather it would wait to be called, so only when the view would need it, the property
    *  will be checked to see if it's initiated*/

    val pokemon by lazyDeferred {
        kantoPokemonRepository.getAll(isBulbasaur)
    }

    private val viewModelJob: CompletableJob = Job()
    val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    suspend fun cleanCache() {
        kantoPokemonRepository.cleanAll()
    }

//    val clients: LiveData<MutableList<Buyer>> = buyerSource.getAll()

    /*private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading*/


    /*suspend fun addBuyer() {
        _isLoading.value = true
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            userSource.insertUser(User("B","B","Luis","B","Buyer"))
            buyerSource.insertBuyer(Buyer("B","B","@gmail.com",1))
            //_clients.postValue(buyerSource.getAll().value)
            _isLoading.postValue(false)
        }
    }*/

}