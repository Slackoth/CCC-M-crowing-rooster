package com.cccm.crowingrooster.network.repository.seller

import com.cccm.crowingrooster.database.daos.SellerClientDao
import com.cccm.crowingrooster.database.daos.SellerFreeDao
import com.cccm.crowingrooster.database.entities.SellerFree

class SellerFreeRepository (
    private val sellerFreeDao: SellerFreeDao
){

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