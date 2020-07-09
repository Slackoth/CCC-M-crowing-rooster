package com.cccm.crowingrooster.network.repository.seller

import android.util.Log
import com.cccm.crowingrooster.database.daos.SellerDao
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import com.cccm.crowingrooster.network.body.ConfirmSaleBody
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConfirmSaleRepository() {

    fun send(confirmSaleBody: ConfirmSaleBody,saleId: String?) {
        GlobalScope.launch {
            try {
                CrowingRoosterApiService.CrowingRoosterApi
                    .retrofitService.sendConfirmSale(confirmSaleBody,saleId)
            }
            catch (e: Exception) {
                Log.d("Connection","No connection: ${e.message}")
            }
        }
    }

    companion object {
        private var INSTANCE: ConfirmSaleRepository? = null

        fun getInstance() = INSTANCE ?: createInstance()
            .also { INSTANCE = it }

        private fun createInstance() = ConfirmSaleRepository()
    }

}