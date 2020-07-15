package com.cccm.crowingrooster.screens.sales.ongoing_sales.confirm_sale

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cccm.crowingrooster.network.body.ConfirmSaleBody
import com.cccm.crowingrooster.network.repository.seller.ConfirmSaleRepository
import kotlinx.coroutines.*

class ConfirmSaleViewModel(
    private val confirmSaleRepository: ConfirmSaleRepository,
    private val saleId: String?,
    private val app: Application
): AndroidViewModel(app) {
    //private val viewModelJob: CompletableJob = Job()
    //private val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var price: Double = 0.0
    private var date: String = ""
    private var hour: String = ""
    private var address: String = ""
    private var payment: String = ""

    fun confirmSale(confirmSaleBody: ConfirmSaleBody) {
        price = confirmSaleBody.price
        date = confirmSaleBody.date
        hour = confirmSaleBody.hour
        address = confirmSaleBody.address
        payment = confirmSaleBody.payment
        //uiScope.launch {
        confirmSaleRepository.send(confirmSaleBody,saleId)
        confirmSaleRepository.deleteSale(saleId)
        //}
    }

}