package com.cccm.crowingrooster.screens.search_battery

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.catalogue.CatalogueRepository

class CatalogueViewModelFactory (
    private val catalogueRepository: CatalogueRepository,
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatalogueViewModel::class.java)) {
            return CatalogueViewModel(catalogueRepository, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}