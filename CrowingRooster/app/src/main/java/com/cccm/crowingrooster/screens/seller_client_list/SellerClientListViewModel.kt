package com.cccm.crowingrooster.screens.seller_client_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.database.daos.BuyerDao
import com.cccm.crowingrooster.database.entities.Buyer
import kotlinx.coroutines.*

class SellerClientListViewModel(
    private val buyerDb: BuyerDao,
    private val app: Application
): AndroidViewModel(app) {
    private val viewModelJob: CompletableJob = Job()
    val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _clients: MutableLiveData<MutableList<Any>> = MutableLiveData()
    val clients: LiveData<MutableList<Any>>
        get() = _clients

    init {
        _clients.value = mutableListOf<Any>(
            Buyer("B","B","B",1)
        )
        //insertClient(Buyer("A","A","A",1))
//        uiScope.launch {
//            //insertClient(Buyer("A","A","A",1))
//
////            _clients.value = getClients()
//        }
    }

    private fun getClients(): List<Any> {

        //withContext(Dispatchers.IO) {
//        val idk = buyerDb.getClientsForSeller()
        //}
//        clientList.addAll(
//            listOf(
//                Client(
//                    "Rene", "rene@gmail.com",
//                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
//                ),
//                Client(
//                    "Luis", "luis@gmail.com",
//                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
//                ),
//                Client(
//                    "Christian", "christian@gmail.com",
//                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
//                ),
//                Client(
//                    "Pipo", "pipo@gmail.com",
//                    "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/54371128_2605774029452750_1474735591550615552_n.jpg?_nc_cat=104&_nc_sid=85a577&_nc_ohc=0YEa9J_uk_EAX_DjfCX&_nc_ht=scontent-mia3-1.xx&oh=b4f0a9a730d6915d632424f62451adf1&oe=5EE56BEB"
//                )
//            )
//        )
        return listOf<Any>(
            Buyer("B","B","B",1)
        )
    }

//    suspend fun insertClient(buyer: Buyer) {
//        withContext(Dispatchers.IO) {
//            buyerDb.insertBuyer(buyer)
//        }
//    }

    suspend fun getClient() {
        val nepe = withContext(Dispatchers.IO) {
            val ab = buyerDb.getClientsForSeller()
            ab
        }
//        _clients.value = listOf<Any>(
//            Buyer("D","D","D",1)
//        )
        val verga = _clients.value
        verga?.add(nepe[0])
        _clients.value = verga

    }

    fun getClientA() {
//        val nepe = withContext(Dispatchers.IO) {
//            val ab = buyerDb.get(id)
//            val ac = listOf<Any>(ab)
//            ac
//        }
//        val nepe = _clients.value
//        nepe?.addAll(
//         mutableListOf<Any>(
//            Buyer("D","D","D",1),
//            Buyer("E","E","E",1)
//        ))
//        _clients.value = nepe

        //_clients.value = nepe
    }

}