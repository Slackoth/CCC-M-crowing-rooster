package com.cccm.crowingrooster.network.repository.catalogue

import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import androidx.lifecycle.LiveData
import com.cccm.crowingrooster.database.daos.CatalogueDao
import com.cccm.crowingrooster.database.entities.Catalogue
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime

class CatalogueRepository (private val catalogueDao: CatalogueDao) {
    fun getAll(): LiveData<List<Catalogue>> {
        refreshCatalogue()
        return catalogueDao.getAll()
    }

    private fun refreshCatalogue() {
        GlobalScope.launch {
            if (isFetchedNeeded(ZonedDateTime.now().minusHours(1))) {
                try {
                    val catalogue = CrowingRoosterApiService.CrowingRoosterApi
                        .retrofitService.getBatteriesCatalogue()
                    for (catalogue in catalogue) {
                        catalogueDao.insert(catalogue)
                    }
                }
                catch (e: Exception) {
                    Log.d("Connection", "No Connection: ${e.message}")
                }
            }
        }
    }

    private fun isFetchedNeeded(lastFetch: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetch.isBefore(thirtyMinAgo)
    }

    companion object {
        private var INSTANCE: CatalogueRepository? = null

        fun getInstance(
            catalogueDao: CatalogueDao
        ) = INSTANCE
            ?: createInstance(
                catalogueDao
            ).also {
                INSTANCE = it
            }

        private fun createInstance(
            catalogueDao: CatalogueDao
        ) =
            CatalogueRepository(
                catalogueDao
            )
    }
}