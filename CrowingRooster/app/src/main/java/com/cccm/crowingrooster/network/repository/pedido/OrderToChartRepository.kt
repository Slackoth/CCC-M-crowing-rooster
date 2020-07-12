package com.cccm.crowingrooster.network.repository.pedido

import android.util.Log
import androidx.room.FtsOptions
import com.cccm.crowingrooster.database.daos.OrdertoChartDao
import com.cccm.crowingrooster.database.daos.PedidoDao
import com.cccm.crowingrooster.database.entities.OrdertoChart
import com.cccm.crowingrooster.database.entities.order.OrderCode
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import com.firebase.ui.auth.ErrorCodes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OrderToChartRepository (
    private val OrdertoChartDao: OrdertoChartDao
){
    private var ordencode= "O-2020-9"

     fun Insert() {
         GlobalScope.launch(Dispatchers.Main) {
//              CrowingRoosterApiService.CrowingRoosterApi.retrofitService.CreateOrder("code")

             Log.d("Caca", "*x2")
             GlobalScope.launch {
                 Log.d("Caca", "suputamadre")
                 try {
                     Log.d("Caca", "Suputamadre*2")
                     CrowingRoosterApiService.CrowingRoosterApi.retrofitService.CreateOrder("code")
                     //Log.d("Caca", Batteries.toString())
                     // Log.d("Bttry", "${Batteries[0].modelo}")
                 } catch (e: Exception) {
                     Log.d("Connection", "No connection In: ${e.message}")
                 }
             }
         }
     }


    fun getCode ():String{
            ///
        GlobalScope.launch (Dispatchers.Main){
//           code =CrowingRoosterApiService.CrowingRoosterApi.retrofitService.getOrderCode("code")
          try {
                Log.d("Caca", "Suputamadre*2")
                var data =CrowingRoosterApiService.CrowingRoosterApi.retrofitService.getOrderCode("code")
                Log.d("Caggada", data[0].codigoorden)
                ordencode=data[0].codigoorden
                return@launch
                //Log.d("Caca", Batteries.toString())
                // Log.d("Bttry", "${Batteries[0].modelo}")
            } catch (e: Exception) {
                Log.d("Connection", "No connection out: ${e.message}")
            }

        }
        return ordencode

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