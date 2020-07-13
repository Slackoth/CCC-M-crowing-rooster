package com.cccm.crowingrooster.network.repository.order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.daos.order.BuyerDao
import com.cccm.crowingrooster.database.entities.order.Buyer
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import com.cccm.crowingrooster.network.body.Company
import com.cccm.crowingrooster.network.body.RegisterBuyer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class RegisterBuyerRepository () {

    fun send(registerBuyer: RegisterBuyer) {
        GlobalScope.launch {
            try {
                CrowingRoosterApiService.CrowingRoosterApi
                    .retrofitService.registerBuyer(registerBuyer)
            }
            catch (e: Exception) {
                Log.d("Connection","No connection: ${e.message}")
            }
        }
    }

    fun getCompany(): MutableLiveData<List<Company>> {
        var company = MutableLiveData<List<Company>>()
        GlobalScope.launch {
            try {
                val companies = CrowingRoosterApiService.CrowingRoosterApi
                    .retrofitService.getCompany()
                Log.d("Connection","${companies}")
                //company.value = companies
                company.postValue(companies)
            }
            catch (e: Exception) {
                Log.d("Connection","No connection: ${e.message}")
            }
        }
        return company
    }

    companion object {
        private var INSTANCE: RegisterBuyerRepository? = null

        fun getInstance() = INSTANCE ?: createInstance()
            .also { INSTANCE = it }

        private fun createInstance() = RegisterBuyerRepository()
    }
}