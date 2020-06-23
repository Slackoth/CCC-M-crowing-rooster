package com.cccm.crowingrooster.screens.seller_client_list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.database.daos.BuyerDao
import com.cccm.crowingrooster.database.daos.UserDao

class SellerClientViewModelFactory(
    private val buyerSource: BuyerDao,
    private val userSource: UserDao,
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SellerClientListViewModel::class.java)) {
            return SellerClientListViewModel(buyerSource,userSource,app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}