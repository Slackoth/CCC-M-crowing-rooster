package com.cccm.crowingrooster.screens.chart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.entities.Pedido
import com.cccm.crowingrooster.database.entities.SalePreview
import com.cccm.crowingrooster.network.repository.pedido.PedidoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryRepository


class ChartViewModel (
    pedidoRepository: PedidoRepository,
    app: Application
): AndroidViewModel(app){

    var pedidos: LiveData<List<Pedido>> = pedidoRepository.getSpecific("16")


     init {

     }
}