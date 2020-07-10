package com.cccm.crowingrooster.network.repository.seller

import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.SellerClientDao
import com.cccm.crowingrooster.database.daos.SellerFreeDao
import com.cccm.crowingrooster.database.entities.Seller
import com.cccm.crowingrooster.database.entities.SellerFree

class SellerFreeRepository (
    private val sellerFreeDao: SellerFreeDao
){
    fun getSpecific(): LiveData<SellerFree> {
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