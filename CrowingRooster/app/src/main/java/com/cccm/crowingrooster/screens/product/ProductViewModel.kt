package com.cccm.crowingrooster.screens.product

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.daos.BatteryDao
import com.cccm.crowingrooster.database.daos.PedidoDao
import com.cccm.crowingrooster.database.entities.Battery
import com.cccm.crowingrooster.network.repository.pedido.PedidoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryInfoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryRepository
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import com.cccm.crowingrooster.database.entities.Pedido as Pedido

class ProductViewModel(
    id_battery: Int,
    BatteryRepository  : BatteryRepository,
    pedidoRepo: PedidoRepository,
    app: Application
): AndroidViewModel(app) {

    var battery: LiveData<Battery> = BatteryRepository.getSpecific(id_battery)
    var DoesItExist= pedidoRepo.doesItExist("23",2)
    var PedidoExistente=Pedido(1,1,1,"","","","")

//    pedidoRepo.

    fun SetIntoChart(cant:Int, IdUser:String, id_battery:Int, pedidoDao: PedidoDao, Desc:String, img:String, titulo:String, flag:Boolean, quant:String){
        var Pedido: Pedido= Pedido(0,cant, id_battery,IdUser,img,Desc, titulo)

        if(flag==false){
            //Log.d("Pedido", DoesItExist)
            PedidoRepository.getInstance(pedidoDao).Insertar(Pedido)
        }else{
            Pedido=PedidoExistente
            Pedido.cantidad_bateria=  Pedido.cantidad_bateria +quant.toInt()
            PedidoRepository.getInstance(pedidoDao).update(Pedido)
        }

    }




}
