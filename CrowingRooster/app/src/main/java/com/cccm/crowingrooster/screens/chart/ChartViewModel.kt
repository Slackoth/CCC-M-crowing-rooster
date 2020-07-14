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
import com.cccm.crowingrooster.database.entities.order.OrderCode
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.UserDatabase
import com.cccm.crowingrooster.network.body.PedidoDatabaseBody
import com.cccm.crowingrooster.network.repository.pedido.OrderToChartRepository
import com.cccm.crowingrooster.network.repository.pedido.PedidoDatabaseRepository
import com.cccm.crowingrooster.network.repository.pedido.PedidoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryRepository
import com.cccm.crowingrooster.network.repository.seller.SellerFreeRepository
import com.cccm.crowingrooster.screens.chat.ChatFragment
import com.cccm.crowingrooster.screens.chat.Messages.ChatMessage
import com.firebase.ui.auth.data.model.User
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.view.View


class ChartViewModel (
    buyerCode:String,
    pedidoRepository: PedidoRepository,
    SellerFreeRepository: SellerFreeRepository,
    OrderToChartRepository:OrderToChartRepository,
    app: Application
): AndroidViewModel(app){

    var pedidos: LiveData<List<Pedido>> = pedidoRepository.getSpecific(buyerCode)
    var freeSeller:LiveData<SellerFree> = SellerFreeRepository.getSpecific()
    var FreeSelleruid:String= ""
    var codigo=OrderToChartRepository.getCode()
    var pedidoRepository=pedidoRepository


    fun doOrderInsert(OrdertoChartDao:OrdertoChartDao, listaPedidos:List<Pedido>, code:String, codeUser:String){
        var Orderep= OrderToChartRepository.getInstance(OrdertoChartDao)
        Orderep.Insert()
//        var code =Orderep.getCode()
        var PedidoDatabaseRepository=  PedidoDatabaseRepository
        for(it in listaPedidos){
      var PedidoDatabaseBody=PedidoDatabaseBody (codeUser,  it.cantidad_bateria, code,it.id_bateria)
            PedidoDatabaseRepository.getInstance().send(PedidoDatabaseBody)
        }
        nukeChart()


    }
    fun nukeChart(){
        pedidoRepository.nukeThen_all()
    }



    fun CreateChat(){
        var mensaje="En este chat te contactaras con un vendedor para negociar el proceso de compra, espera mientras se contactará contigo"

//
//        val sellerRef= FirebaseAuth.getInstance().getUserByEmail("de")
        val toId= FirebaseAuth.getInstance().uid.toString()
        val fromId  = FreeSelleruid
            //"4awXCfrkKKcBwORmVMo7mU8IPL13"
        val reference= FirebaseDatabase.getInstance().getReference("/user_messages/$fromId/$toId").push()
        val chatMessage= ChatMessage(reference.key!!, mensaje, fromId,toId, System.currentTimeMillis()/1000)
        val toReference= FirebaseDatabase.getInstance().getReference("/user_messages/$toId/$fromId").push()
        reference.setValue(chatMessage)
            .addOnSuccessListener {
                //Log.d("Chart","Mensaje Subido Chartos")
            }
        toReference.setValue(chatMessage)
            .addOnSuccessListener {
                //Log.d("Chart","Chartos")
            }
    }

    fun getSellerUId(email:String){
        //Log.d("Useruid", email)
        val sellerRef= FirebaseDatabase.getInstance().getReference("/users")
        sellerRef.addChildEventListener(object :ChildEventListener{
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                //TODO("Not yet implemented")
                val UserDatabase= p0.getValue(UserDatabase::class.java)
                if (UserDatabase != null) {
                    //Log.d("Useruid", "no es unulo UserDB")
                    if (UserDatabase.username==email.toLowerCase()){
                      //  Log.d("Useruid", UserDatabase.username)
                        FreeSelleruid= UserDatabase.uid
                        //Log.d("Useruid", FreeSelleruid + " Este deberia ser el uid ")
                    }
                }

            }
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("Not yet implemented")
            }
        })



    }

    fun IncreasedClicked(view:View){
       // Log.d("databuttom", "si me sumas bb")
    }


//


    init {

     }
}