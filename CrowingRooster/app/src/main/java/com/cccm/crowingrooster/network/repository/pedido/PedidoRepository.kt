package com.cccm.crowingrooster.network.repository.pedido

import android.os.health.UidHealthStats
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.BatteryDao
import com.cccm.crowingrooster.database.daos.PedidoDao
import com.cccm.crowingrooster.database.entities.BatteryInfo
import com.cccm.crowingrooster.database.entities.Pedido
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import com.cccm.crowingrooster.network.repository.product.BatteryRepository

class PedidoRepository (private val PedidoDao: PedidoDao){

//    companion object {
//        private var INSTANCE: PedidoRepository? = null
//
//        fun getInstance(
//            PedidoDao: PedidoDao
//        ) = INSTANCE
//            ?: createInstance(
//                PedidoDao
//            ).also {
//                INSTANCE = it
//            }
//
//        private fun createInstance(
//            pedido: Pedido
//        ) =
//            PedidoRepository(
//                PedidoDao
//            )
//    }
    fun Insertar(Pedido:Pedido){
        PedidoDao.insert(Pedido)
    }

    fun getSpecific(userUid : String): LiveData<List<Pedido>> {
        return PedidoDao.getSpecific(userUid)
    }

    fun doesItExist(userUid: String, id_bateria:Int ):LiveData<Pedido>{
        return PedidoDao.doesItExist(userUid, id_bateria)
    }

    fun update(Pedido: Pedido){
        return PedidoDao.UpdatePedido(Pedido)
    }

    fun nukeThen_all(){
        PedidoDao.nukeTable()
    }


    companion object {
        private var INSTANCE: PedidoRepository? = null

        fun getInstance(
            PedidoDao: PedidoDao
        ) = INSTANCE
            ?: createInstance(
                PedidoDao
            ).also {
                INSTANCE = it
            }

        private fun createInstance(
            PedidoDao: PedidoDao
        ) =
            PedidoRepository(
                PedidoDao
            )
    }

}