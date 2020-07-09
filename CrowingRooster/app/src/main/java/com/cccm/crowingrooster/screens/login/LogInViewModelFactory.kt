package com.cccm.crowingrooster.screens.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.login.LogInRepository
import com.cccm.crowingrooster.network.repository.seller.SellerClientRepository
import com.cccm.crowingrooster.screens.seller_client_list.SellerClientListViewModel

class LogInViewModelFactory(
    private val logInRepository: LogInRepository,
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogInViewModel::class.java)) {
            return LogInViewModel(logInRepository,app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}