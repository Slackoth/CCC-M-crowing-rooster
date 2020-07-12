package com.cccm.crowingrooster.network.repository.pedido

import android.util.Log
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import com.cccm.crowingrooster.network.body.ConfirmSaleBody
import com.cccm.crowingrooster.network.body.PedidoDatabaseBody
import com.cccm.crowingrooster.network.repository.seller.ConfirmSaleRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PedidoDatabaseRepository {

    fun send(PedidoDatabaseBody:PedidoDatabaseBody) {
        GlobalScope.launch {
            try {
                CrowingRoosterApiService.CrowingRoosterApi
                    .retrofitService.sendPedido(PedidoDatabaseBody)
            }
            catch (e: Exception) {
                Log.d("Connection","No connection: ${e.message}")
            }
        }
    }

    companion object {
        private var INSTANCE: PedidoDatabaseRepository? = null

        fun getInstance() = INSTANCE ?: createInstance()
            .also { INSTANCE = it }

        private fun createInstance() = PedidoDatabaseRepository()
    }





}