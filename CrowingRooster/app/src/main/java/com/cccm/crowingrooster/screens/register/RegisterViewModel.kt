package com.cccm.crowingrooster.screens.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cccm.crowingrooster.database.entities.order.Buyer
import com.cccm.crowingrooster.network.body.Company
import com.cccm.crowingrooster.network.body.RegisterBuyer
import com.cccm.crowingrooster.network.repository.order.RegisterBuyerRepository

class RegisterViewModel(
    private val registerBuyerRepository: RegisterBuyerRepository,
    private val app: Application
):AndroidViewModel(app){

//    var name: String = ""
//    var company: String = ""
//    var dui: String = ""
//    var email: String = ""
//    var telefono: String = ""
//    var img: String = ""
    val companies: LiveData<List<Company>>

    init {
        companies = registerBuyerRepository.getCompany()
    }

    fun register(registerBuyer: RegisterBuyer) {
        registerBuyerRepository.send(registerBuyer)
    }

    override fun onCleared() {
        super.onCleared()
    }
}
