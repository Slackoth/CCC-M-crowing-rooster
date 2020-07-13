package com.cccm.crowingrooster.screens.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.order.BuyerProfileRepository
import com.cccm.crowingrooster.network.repository.order.RegisterBuyerRepository
import com.cccm.crowingrooster.screens.buyer_profile.BuyerProfileViewModel

class RegisterViewModelFactory(
    private val registerBuyerRepository: RegisterBuyerRepository,
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(registerBuyerRepository,app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}