package com.cccm.crowingrooster.database.daos

import android.net.wifi.aware.PeerHandle
import androidx.lifecycle.LiveData
import androidx.room.*
import com.cccm.crowingrooster.database.entities.Battery
import com.cccm.crowingrooster.database.entities.Pedido
import com.cccm.crowingrooster.database.entities.SalePreview


@Dao
interface PedidoDao {

    @Insert
    fun insert(Pedido:Pedido)

    @Query("SELECT * FROM chart c WHERE c.id_usuario = :usr_id")
    fun getSpecific(usr_id:String): LiveData<List<Pedido>>

    @Query("SELECT * FROM chart c WHERE c.id_usuario=:usr_id and C.id_bateria=:id_bateria")
    fun doesItExist(usr_id:String,id_bateria:Int ): LiveData<Pedido>

    @Update
    fun UpdatePedido(Pedido: Pedido)

}