package com.cccm.crowingrooster.screens.sales.ongoing_sales.ongoing_sale_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.SaleDetails

class OngoingSalesDetailsViewModel: ViewModel() {
    private var _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String>
        get() = _name

    private var _email: MutableLiveData<String> = MutableLiveData()
    val email: LiveData<String>
        get() = _email

    private var _totalQuantity: MutableLiveData<Int> = MutableLiveData()
    val totalQuantity: LiveData<Int>
        get() = _totalQuantity

    private var _date: MutableLiveData<String> = MutableLiveData()
    val date: LiveData<String>
        get() = _date

    private var __orders: MutableLiveData<MutableList<Any>> = MutableLiveData()
    val orders: LiveData<MutableList<Any>>
        get() = __orders

    init {
        _name.value = "Mr. Peanutbutter"
        _email.value = "ungallocontenis@gmail.com"
        _totalQuantity.value = 69
        _date.value = "20/08/1969"
        __orders.value = setOrders()
    }

    private fun setOrders(): MutableList<Any> {
        val ordersList = mutableListOf<Any>()
        ordersList.addAll(
            listOf(
                SaleDetails(
                    10,
                    "20F-Derecha-Azul"
                ),
                SaleDetails(
                    15,
                    "21D-Izquierda-Amarilla"
                ),
                SaleDetails(
                    2,
                    "22E-Derecha-Amarilla"
                ),
                SaleDetails(
                    30,
                    "23Q-Izquierda-Azul"
                ),
                SaleDetails(
                    6,
                    "24P-Derecha-Azul"
                )
            )
        )
        return ordersList
    }


}