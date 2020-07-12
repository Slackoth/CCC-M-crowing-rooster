package com.cccm.crowingrooster.screens.chart

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.FtsOptions
import com.cccm.crowingrooster.database.daos.OrdertoChartDao
import com.cccm.crowingrooster.database.daos.PedidoDao
import com.cccm.crowingrooster.database.entities.OrdertoChart
import com.cccm.crowingrooster.database.entities.Pedido
import com.cccm.crowingrooster.database.entities.SalePreview
import com.cccm.crowingrooster.database.entities.SellerFree
import com.cccm.crowingrooster.network.body.PedidoDatabaseBody
import com.cccm.crowingrooster.network.repository.pedido.OrderToChartRepository
import com.cccm.crowingrooster.network.repository.pedido.PedidoDatabaseRepository
import com.cccm.crowingrooster.network.repository.pedido.PedidoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryRepository
import com.cccm.crowingrooster.network.repository.seller.SellerFreeRepository
import com.cccm.crowingrooster.screens.chat.ChatFragment
import com.cccm.crowingrooster.screens.chat.Messages.ChatMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class ChartViewModel (
    pedidoRepository: PedidoRepository,
    SellerFreeRepository: SellerFreeRepository,
    OrderToChartRepository:OrderToChartRepository,
    app: Application
): AndroidViewModel(app){

    var pedidos: LiveData<List<Pedido>> = pedidoRepository.getSpecific("16")
    var freeSeller= SellerFreeRepository.getSpecific()
    //var OrdertoChart=com.cccm.crowingrooster.database.entities.OrdertoChart("")



    fun doOrderInsert(OrdertoChartDao:OrdertoChartDao, listaPedidos:List<Pedido>){
        var Orderep= OrderToChartRepository.getInstance(OrdertoChartDao)
        Orderep.Insert()
        var code =Orderep.getCode()
        var PedidoDatabaseRepository=  PedidoDatabaseRepository
        for(it in listaPedidos){
            var PedidoDatabaseBody=PedidoDatabaseBody ("C-2020-0",  it.cantidad_bateria, code,it.id_bateria)
            PedidoDatabaseRepository.getInstance().send(PedidoDatabaseBody)
        }

    }


    fun CreateChat(){
        var mensaje="En este chat te contactaras con un vendedor para negociar el proceso de compra, espera mientras se contactará contigo"

        val fromId= FirebaseAuth.getInstance().uid.toString()
        val toId= "4awXCfrkKKcBwORmVMo7mU8IPL13"
        val reference= FirebaseDatabase.getInstance().getReference("/user_messages/$fromId/$toId").push()
        val chatMessage= ChatMessage(reference.key!!, mensaje, fromId,toId, System.currentTimeMillis()/1000)
        val toReference= FirebaseDatabase.getInstance().getReference("/user_messages/$toId/$fromId").push()
        reference.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d("Chart","Mensaje Subido Chartos")


            }
        toReference.setValue(chatMessage)
            .addOnSuccessListener {
                Log.d("Chart","Chartos")
            }
    }

//


    init {

     }
}