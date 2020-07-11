package com.cccm.crowingrooster.network.repository.order

import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.order.BuyerDao
import com.cccm.crowingrooster.database.entities.order.Buyer
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import com.cccm.crowingrooster.network.body.RegisterBuyer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class RegisterBuyerRepository () {

    fun send(registerBuyer: RegisterBuyer) {
        GlobalScope.launch {
            try {
                CrowingRoosterApiService.CrowingRoosterApi
                    .retrofitService.registerBuyer(registerBuyer)
            }
            catch (e: Exception) {
                Log.d("Connection","No connection: ${e.message}")
            }
        }
    }

    companion object {
        private var INSTANCE: RegisterBuyerRepository? = null

        fun getInstance() = INSTANCE ?: createInstance()
            .also { INSTANCE = it }

        private fun createInstance() = RegisterBuyerRepository()
    }
}