package com.cccm.crowingrooster.network.repository.seller

import android.util.Log
import com.cccm.crowingrooster.database.daos.SalePreviewDao
import com.cccm.crowingrooster.database.daos.SellerDao
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import com.cccm.crowingrooster.network.body.ConfirmSaleBody
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConfirmSaleRepository(private val salePreviewDao: SalePreviewDao) {

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

    fun deleteSale(saleId: String?) {
        salePreviewDao.deleteSale(saleId)
    }

    companion object {
        private var INSTANCE: ConfirmSaleRepository? = null

        fun getInstance(salePreviewDao: SalePreviewDao) = INSTANCE ?: createInstance(salePreviewDao)
            .also { INSTANCE = it }

        private fun createInstance(salePreviewDao: SalePreviewDao) = ConfirmSaleRepository(salePreviewDao)
    }

}