package com.cccm.crowingrooster.screens.product

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.daos.BatteryDao
import com.cccm.crowingrooster.database.daos.PedidoDao
import com.cccm.crowingrooster.database.entities.Battery
import com.cccm.crowingrooster.database.entities.Catalogue
import com.cccm.crowingrooster.network.repository.catalogue.CatalogueRepository
import com.cccm.crowingrooster.network.repository.pedido.PedidoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryInfoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryRepository
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import com.cccm.crowingrooster.database.entities.Pedido as Pedido

class ProductViewModel(

    id_bateria : Int?,
    BatteryRepository  : BatteryRepository,
    pedidoRepo: PedidoRepository,
    app: Application
): AndroidViewModel(app) {


    var battery: LiveData<Battery> = BatteryRepository.getSpecific(id_bateria)

//    pedidoRepo.

    fun SetIntoChart(cant:Int, IdUser:String, id_battery:Int, pedidoDao: PedidoDao, Desc:String, img:String, titulo:String){

        var Pedido: Pedido= Pedido(0,cant, id_battery,IdUser,img,Desc, titulo)

        if(PedidoRepository.getInstance(pedidoDao).doesItExist(IdUser,id_battery).value==null){
            Log.d("Pedido", "Ta lleno")
            PedidoRepository.getInstance(pedidoDao).Insertar(Pedido)
        }else{
            Log.d("Pedido", "Ta vacio")
            Pedido=PedidoRepository.getInstance(pedidoDao).doesItExist(IdUser,id_battery).value!!
            Pedido.cantidad_bateria= Pedido.cantidad_bateria+1
            PedidoRepository.getInstance(pedidoDao).update(Pedido)
        }

    }
}


