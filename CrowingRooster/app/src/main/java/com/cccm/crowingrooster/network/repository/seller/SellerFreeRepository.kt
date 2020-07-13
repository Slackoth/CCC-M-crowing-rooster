package com.cccm.crowingrooster.network.repository.seller

import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.SellerClientDao
import com.cccm.crowingrooster.database.daos.SellerFreeDao
import com.cccm.crowingrooster.database.entities.Seller
import com.cccm.crowingrooster.database.entities.SellerFree
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SellerFreeRepository (
    private val sellerFreeDao: SellerFreeDao
){
    fun getSpecific(): LiveData<SellerFree> {
        GlobalScope.launch (Dispatchers.Main){
//           code =CrowingRoosterApiService.CrowingRoosterApi.retrofitService.getOrderCode("code")
            try {
                Log.d("CacaSeller", "Suputamadre*2")
                var data = CrowingRoosterApiService.CrowingRoosterApi.retrofitService.getSellerfree()
                for (SellerFree in data) {
                    Log.d("CacaSeller", SellerFree.email)

                    sellerFreeDao.insert(SellerFree)
                }
            } catch (e: Exception) {
                Log.d("Connection", "No connection out: ${e.message}")
            }


        }




        return sellerFreeDao.getone()

    }




    companion object {
        private var INSTANCE: SellerFreeRepository? = null

        fun getInstance(
            SellerfreeDao: SellerFreeDao
        ) = INSTANCE
            ?: createInstance(
                SellerfreeDao
            ).also {
                INSTANCE = it
            }

        private fun createInstance(
            SellerfreeDao:SellerFreeDao
        ) =
            SellerFreeRepository(
                SellerfreeDao
            )
    }
}