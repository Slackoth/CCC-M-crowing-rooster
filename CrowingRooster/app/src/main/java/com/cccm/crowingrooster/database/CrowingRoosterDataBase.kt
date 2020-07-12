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
import com.cccm.crowingrooster.database.daos.order.OrderDetailsDao
import com.cccm.crowingrooster.database.daos.order.OrderMiniOrderDao
import com.cccm.crowingrooster.database.daos.order.OrderPreviewDao
import com.cccm.crowingrooster.database.entities.*
import com.cccm.crowingrooster.database.entities.order.OrderDetails
import com.cccm.crowingrooster.database.entities.order.OrderMiniOrder
import com.cccm.crowingrooster.database.entities.order.OrderPreview


@Database(entities = [SellerClient::class,Seller::class,SalePreview::class, SaleDetails::class,SaleMiniOrders::class,
User::class,OrderPreview::class,OrderDetails::class,OrderMiniOrder::class,Battery::class, BatteryInfo::class, Pedido::class, SellerFree::class,DeliveryPreview::class,Catalogue::class],version = 33,exportSchema = false)


abstract class CrowingRoosterDataBase: RoomDatabase() {

    abstract val sellerClientDao: SellerClientDao
    abstract val sellerDao: SellerDao
    abstract val batteryDao:BatteryDao
    abstract val salePreviewDao: SalePreviewDao
    abstract val saleDetailsDao: SaleDetailsDao
    abstract val saleMiniOrdersDao: SaleMiniOrdersDao
    abstract val deliveryPreviewDao: DeliveryPreviewDao

    abstract val BatteryInfoDao:BatteryInfoDao
    abstract val PedidoDao:PedidoDao
    abstract val sellerFreeDao:SellerFreeDao


    abstract val userDao: UserDao
    abstract val orderPreviewDao: OrderPreviewDao
    abstract val orderDetailsDao: OrderDetailsDao
    abstract val orderMiniOrdersDao: OrderMiniOrderDao
    abstract val catalogueDao: CatalogueDao

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
