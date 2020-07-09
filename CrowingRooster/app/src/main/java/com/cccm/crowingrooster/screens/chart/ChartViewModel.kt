package com.cccm.crowingrooster.screens.chart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.entities.Pedido
import com.cccm.crowingrooster.database.entities.SalePreview
import com.cccm.crowingrooster.database.entities.SellerFree
import com.cccm.crowingrooster.network.repository.pedido.PedidoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryRepository
import com.cccm.crowingrooster.network.repository.seller.SellerFreeRepository


class ChartViewModel (
    pedidoRepository: PedidoRepository,
    sellerFreeRepository: SellerFreeRepository,
    app: Application
): AndroidViewModel(app){

    var pedidos: LiveData<List<Pedido>> = pedidoRepository.getSpecific("16")


     init {

     }
}