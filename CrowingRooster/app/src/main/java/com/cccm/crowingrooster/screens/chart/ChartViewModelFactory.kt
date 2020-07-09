package com.cccm.crowingrooster.screens.chart

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.pedido.PedidoRepository
import com.cccm.crowingrooster.network.repository.seller.SellerFreeRepository
import com.cccm.crowingrooster.network.repository.seller.SellerRepository
import com.cccm.crowingrooster.screens.seller_profile.SellerProfileViewModel

class ChartViewModelFactory(
    private val PedidoRepository: PedidoRepository,
    private val SellerFreeRepository:SellerFreeRepository,
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChartViewModel::class.java)) {
            return ChartViewModel( PedidoRepository, SellerFreeRepository,app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}