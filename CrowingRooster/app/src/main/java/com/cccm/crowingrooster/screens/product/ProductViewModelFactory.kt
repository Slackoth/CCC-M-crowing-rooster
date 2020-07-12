package com.cccm.crowingrooster.screens.product

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cccm.crowingrooster.network.repository.pedido.PedidoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryInfoRepository
import com.cccm.crowingrooster.network.repository.product.BatteryRepository
import com.cccm.crowingrooster.screens.seller_profile.SellerProfileViewModel

class ProductViewModelFactory(
    private val id_bateria: Int?,
    private  val batteryRepository : BatteryRepository,
    private val PedidoRepository: PedidoRepository,
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(id_bateria, batteryRepository, PedidoRepository,app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}