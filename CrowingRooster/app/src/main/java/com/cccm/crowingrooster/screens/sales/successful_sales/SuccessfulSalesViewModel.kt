package com.cccm.crowingrooster.screens.sales.successful_sales

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.Sale

class SuccessfulSalesViewModel: ViewModel() {
    private var _successfulSales: MutableLiveData<MutableList<Any>> =  MutableLiveData()
    val successfulSalesViewModel: LiveData<MutableList<Any>>
        get() = _successfulSales

    init {
        _successfulSales.value = setSuccessfulSales()
    }

    //TODO: In this method, we should be filling the list with data from the DB
    private fun setSuccessfulSales(): MutableList<Any> {
        val saleList: MutableList<Any> = mutableListOf()
        saleList.addAll(
            listOf(
                Sale(
                    "Rene", "20/08/1969", 5,
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                ),
                Sale(
                    "Luis",
                    "20/08/1969",
                    10,
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                ),
                Sale(
                    "Christian",
                    "20/08/1969",
                    15,
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                ),
                Sale(
                    "Pipo",
                    "20/08/1969",
                    20,
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                )
            )
        )
        return saleList
    }

    //TODO: Example of how the updating method could be
//    fun addSale(sale: Sale) {
//        val saleList = _successfulSales.value
//        saleList?.add(sale)
//        _successfulSales.value = saleList
//    }
}