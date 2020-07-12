package com.cccm.crowingrooster.screens.search_battery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.entities.Catalogue
import com.cccm.crowingrooster.network.repository.catalogue.CatalogueRepository

class CatalogueViewModel (
    private val catalogueRepository: CatalogueRepository,
    private val app : Application
): AndroidViewModel(app) {


    var batteriesOnStock: LiveData<List<Catalogue>> = catalogueRepository.getAll()



    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        batteriesOnStock = catalogueRepository.getAll().also { _isLoading.postValue(false) }
    }

    fun refresh() {
        batteriesOnStock = catalogueRepository.getAll()
    }
}