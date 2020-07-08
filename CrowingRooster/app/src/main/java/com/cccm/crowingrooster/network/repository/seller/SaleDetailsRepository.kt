package com.cccm.crowingrooster.network.repository.seller

import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.SaleMiniOrdersDao
import com.cccm.crowingrooster.database.daos.SaleDetailsDao
import com.cccm.crowingrooster.database.entities.SaleDetails
import com.cccm.crowingrooster.database.entities.SaleMiniOrders
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class SaleDetailsRepository(
    private val saleDetailsDao: SaleDetailsDao,
    private val saleMiniOrdersDao: SaleMiniOrdersDao
) {

    fun getSpecific(code: String?, orderId: String?, state: String): LiveData<SaleDetails> {
        refreshSuccessfulSaleDetails(code,orderId,state)
        return saleDetailsDao.getSpecific(code)
    }

    fun getAll(state: String,saleId:String?): LiveData<List<SaleMiniOrders>> {
        return saleMiniOrdersDao.getAll(state,saleId)
    }

    private fun refreshSuccessfulSaleDetails(code: String?, orderId: String?, state: String) {
        if (isFetchedNeeded(ZonedDateTime.now().minusHours(1))) {
            GlobalScope.launch {
                try {
                    val saleDetails = when(state) {
                        "Exitosa" -> CrowingRoosterApiService.CrowingRoosterApi
                            .retrofitService.getSuccessfulSaleDetailsAsync(code,orderId)
                        else -> CrowingRoosterApiService.CrowingRoosterApi
                            .retrofitService.getOngoingSaleDetailsAsync(code,orderId)
                    }
                    Log.d("saleDetails","$saleDetails")
                    Log.d("saleDetails","${saleDetails[0].miniorder}")

                    for (sale in saleDetails) {
                        Log.d("sale","$sale")
                        saleDetailsDao.insert(sale)
                    }
                    for (miniOrder in saleDetails[0].miniorder) {
                        Log.d("miniOrder","$miniOrder")
                        //miniOrder.clientName = saleDetails[0].name
                        saleMiniOrdersDao.insert(miniOrder)
                    }

                }
                catch (e: Exception) {
                    Log.d("Connection","No connection: ${e.message}")
                }
            }
        }
    }

    private fun isFetchedNeeded(lastFetch: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetch.isBefore(thirtyMinAgo)
    }

    companion object {
        private var INSTANCE: SaleDetailsRepository? = null

        fun getInstance(
            saleDetailsDao: SaleDetailsDao,
            saleMiniOrdersDao: SaleMiniOrdersDao
        ) = INSTANCE ?: createInstance(saleDetailsDao,saleMiniOrdersDao)
            .also { INSTANCE = it }

        private fun createInstance(
            saleDetailsDao: SaleDetailsDao,
            saleMiniOrdersDao: SaleMiniOrdersDao
        ) = SaleDetailsRepository(saleDetailsDao,saleMiniOrdersDao)
    }
}