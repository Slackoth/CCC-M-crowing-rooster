package com.cccm.crowingrooster.network.repository.order

import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.SaleDetailsDao
import com.cccm.crowingrooster.database.daos.SaleMiniOrdersDao
import com.cccm.crowingrooster.database.daos.order.OrderDetailsDao
import com.cccm.crowingrooster.database.daos.order.OrderMiniOrderDao
import com.cccm.crowingrooster.database.entities.SaleDetails
import com.cccm.crowingrooster.database.entities.SaleMiniOrders
import com.cccm.crowingrooster.database.entities.order.OrderDetails
import com.cccm.crowingrooster.database.entities.order.OrderMiniOrder
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import com.cccm.crowingrooster.network.repository.seller.SaleDetailsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class OrderDetailsRepository(
    private val orderDetailsDao: OrderDetailsDao,
    private val orderMiniOrderDao: OrderMiniOrderDao
) {

    fun getSpecific(code: String?, orderId: String?, state: String): LiveData<OrderDetails> {
        refreshSuccessfulSaleDetails(code,orderId,state)
        return orderDetailsDao.getSpecific(code)
    }

    fun getAll(state: String,orderId:String?): LiveData<List<OrderMiniOrder>> {
        return orderMiniOrderDao.getAll(state,orderId)
    }

    private fun refreshSuccessfulSaleDetails(code: String?, orderId: String?, state: String) {
        if (isFetchedNeeded(ZonedDateTime.now().minusHours(1))) {
            GlobalScope.launch {
                try {
                    val orderDetails = when(state) {
                        "Exitosa" -> CrowingRoosterApiService.CrowingRoosterApi
                            .retrofitService.getSuccessfulOrderDetailsAsync(code,orderId)
                        "Pendiente" -> CrowingRoosterApiService.CrowingRoosterApi
                            .retrofitService.getOngoingOrderDetailsAsync(code,orderId)
                        else -> listOf()
                    }
                    Log.d("orderDetails","$orderDetails")
                    Log.d("orderDetails","${orderDetails[0].orderMiniOrder}")

                    for (order in orderDetails) {
                        Log.d("order","$order")
                        orderDetailsDao.insert(order)
                    }
                    for (orderMiniOrder in orderDetails[0].orderMiniOrder) {
                        Log.d("orderMiniOrder","$orderMiniOrder")
                        //miniOrder.clientName = saleDetails[0].name
                        orderMiniOrderDao.insert(orderMiniOrder)
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
        private var INSTANCE: OrderDetailsRepository? = null

        fun getInstance(
            orderDetailsDao: OrderDetailsDao,
            orderMiniOrderDao: OrderMiniOrderDao
        ) = INSTANCE ?: createInstance(orderDetailsDao,orderMiniOrderDao)
            .also { INSTANCE = it }

        private fun createInstance(
            orderDetailsDao: OrderDetailsDao,
            orderMiniOrderDao: OrderMiniOrderDao
        ) = OrderDetailsRepository(orderDetailsDao,orderMiniOrderDao)
    }

}