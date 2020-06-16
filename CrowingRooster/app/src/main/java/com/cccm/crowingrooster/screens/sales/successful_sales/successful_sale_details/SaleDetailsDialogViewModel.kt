package com.cccm.crowingrooster.screens.sales.successful_sales.successful_sale_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SaleDetailsDialogViewModel: ViewModel() {
    private var _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String>
        get() = _name

    private var _email: MutableLiveData<String> = MutableLiveData()
    val email: LiveData<String>
        get() = _email

    private var _totalQuantity: MutableLiveData<Int> = MutableLiveData()
    val totalQuantity: LiveData<Int>
        get() = _totalQuantity

    private var _price: MutableLiveData<Double> = MutableLiveData()
    val price: LiveData<Double>
        get() = _price

    private var _payment: MutableLiveData<String> = MutableLiveData()
    val payment: LiveData<String>
        get() = _payment

    private var _date: MutableLiveData<String> = MutableLiveData()
    val date: LiveData<String>
        get() = _date

    init {
        _name.value = "Mr. Peanutbutter"
        _email.value = "ungallocontenis@gmail.com"
        _totalQuantity.value = 69
        _price.value = 250.50
        _payment.value = "Efectivo"
        _date.value = "20/08/1969"
    }

}