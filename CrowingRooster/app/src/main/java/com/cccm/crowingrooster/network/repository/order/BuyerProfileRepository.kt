package com.cccm.crowingrooster.network.repository.order

import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.SellerDao
import com.cccm.crowingrooster.database.daos.order.BuyerDao
import com.cccm.crowingrooster.database.entities.Seller
import com.cccm.crowingrooster.database.entities.order.Buyer
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import com.cccm.crowingrooster.network.repository.seller.SellerRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class BuyerProfileRepository(private val buyerDao: BuyerDao) {
    fun getSpecific(code: String?): LiveData<Buyer> {
        refreshSeller(code)
        return buyerDao.getBuyer(code)
    }

    private fun refreshSeller(code: String?) {
        if (isFetchedNeeded(ZonedDateTime.now().minusHours(1))) {
            GlobalScope.launch {
                try {
                    val buyers = CrowingRoosterApiService.CrowingRoosterApi
                        .retrofitService.getBuyerAsync(code)
                    Log.d("buyers","${buyers}")
                    for (buyer in buyers) {
                        Log.d("buyer","${buyer}")
                        buyerDao.insert(buyer)
                    }
                }
                catch (e: Exception) {
                    Log.d("Connection","No connection: ${e.message}")
                }
            }
        }
    }

    private fun isFetchedNeeded(lastFetch: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetch.isBefore(thirtyMinAgo)
    }

    companion object {
        private var INSTANCE: BuyerProfileRepository? = null

        fun getInstance(buyerDao: BuyerDao) = INSTANCE ?: createInstance(buyerDao)
            .also { INSTANCE = it }

        private fun createInstance(buyerDao: BuyerDao) = BuyerProfileRepository(buyerDao)
    }
}