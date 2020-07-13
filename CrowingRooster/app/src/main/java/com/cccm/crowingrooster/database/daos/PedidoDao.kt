package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cccm.crowingrooster.database.entities.Pedido


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

    @Query("DELETE FROM chart")
    fun nukeTable()

}