package com.cccm.crowingrooster.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.cccm.crowingrooster.database.daos.BatteryDao
import com.cccm.crowingrooster.database.daos.SellerClientDao
import com.cccm.crowingrooster.database.daos.SellerDao
import com.cccm.crowingrooster.database.entities.Battery
import com.cccm.crowingrooster.database.entities.Seller
import com.cccm.crowingrooster.database.entities.SellerClient



import com.cccm.crowingrooster.database.daos.*
import com.cccm.crowingrooster.database.entities.*


@Database(entities = [SellerClient::class,Seller::class,SalePreview::class, SaleDetails::class,SaleMiniOrders::class,  Battery::class],version = 22 ,exportSchema = false)

abstract class CrowingRoosterDataBase: RoomDatabase() {

    abstract val sellerClientDao: SellerClientDao
    abstract val sellerDao: SellerDao
    abstract val batteryDao:BatteryDao
    abstract val salePreviewDao: SalePreviewDao
    abstract val saleDetailsDao: SaleDetailsDao
    abstract val saleMiniOrdersDao: SaleMiniOrdersDao


    companion object {
        @Volatile
        private var INSTANCE: CrowingRoosterDataBase? = null

        fun getInstance(context: Context): CrowingRoosterDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = buildDataBase(context)
                    INSTANCE = instance
                }

                return instance
            }
        }

        private fun buildDataBase(context: Context): CrowingRoosterDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                CrowingRoosterDataBase::class.java,
                "crowing_rooster_db"
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}
