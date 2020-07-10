package com.cccm.crowingrooster.network.repository.pedido

import com.cccm.crowingrooster.database.daos.OrdertoChartDao
import com.cccm.crowingrooster.database.daos.PedidoDao
import com.cccm.crowingrooster.database.entities.OrdertoChart

class OrderToChartRepository (
    private val OrdertoChartDao: OrdertoChartDao
){


    fun Insert(OrdertoChart: OrdertoChart){
        OrdertoChartDao.Insert(OrdertoChart)
    }


    companion object {
        private var INSTANCE: OrderToChartRepository? = null

        fun getInstance(
            OrdertoChartDao:OrdertoChartDao
        ) = INSTANCE
            ?: createInstance(
                OrdertoChartDao
            ).also {
                INSTANCE = it
            }

        private fun createInstance(
            OrdertoChartDao: OrdertoChartDao
        ) =
            OrderToChartRepository(
                OrdertoChartDao
            )
    }

}