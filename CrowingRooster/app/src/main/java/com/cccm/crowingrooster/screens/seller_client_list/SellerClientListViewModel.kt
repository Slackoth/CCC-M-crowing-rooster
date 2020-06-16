package com.cccm.crowingrooster.screens.seller_client_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cccm.crowingrooster.generic_recyclerview_adapter.models.Client

class SellerClientListViewModel: ViewModel() {
    private var _clients: MutableLiveData<MutableList<Any>> = MutableLiveData()
    val clients: LiveData<MutableList<Any>>
        get() = _clients

    init {
        _clients.value = setClients()
    }

    private fun setClients(): MutableList<Any> {
        val clientList = mutableListOf<Any>()
        clientList.addAll(
            listOf(
                Client(
                    "Rene", "rene@gmail.com",
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                ),
                Client(
                    "Luis", "luis@gmail.com",
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                ),
                Client(
                    "Christian", "christian@gmail.com",
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                ),
                Client(
                    "Pipo", "pipo@gmail.com",
                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
                )
            )
        )
        return clientList
    }

}