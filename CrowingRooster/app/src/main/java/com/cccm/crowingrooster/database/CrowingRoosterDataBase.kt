package com.cccm.crowingrooster.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cccm.crowingrooster.database.daos.*
import com.cccm.crowingrooster.database.daos.SuccessfulSaleAndDeliveryDao
import com.cccm.crowingrooster.database.entities.*

@Database(entities = [User::class,Quality::class,Polarity::class,Battery::class,Buyer::class,
    CanceledOrder::class,Company::class,Delivery::class,DeliveryState::class,DeliveryMan::class,
    DeliveryManAndDelivery::class,Seller::class/*,Email::class*/,MiniOrder::class,Order::class,
    OngoingOrder::class,OngoingSale::class,Phone::class,PaymentMethod::class,Sales::class,
    SuccessfulOrder::class,SuccessfulSale::class,SuccessfulSaleAndDelivery::class],version = 1,exportSchema = false)
abstract class CrowingRoosterDataBase: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val qualityDao: QualityDao
    abstract val polarityDao: PolarityDao
    abstract val batteryDao: BatteryDao
    abstract val buyerDao: BuyerDao
    abstract val canceledOrderDao: CanceledOrderDao
    abstract val companyDao: CompanyDao
    abstract val deliveryDao: DeliveryDao
    abstract val deliveryStateDao: DeliveryStateDao
    abstract val deliveryManDao: DeliveryManDao
    abstract val deliveryManAndDeliveryDao: DeliveryManAndDeliveryDao
    abstract val sellerDao: SellerDao
    abstract val miniOrderDao: MiniOrderDao
    abstract val orderDao: OrderDao
    abstract val ongoingSaleDao: OngoingSaleDao
    abstract val ongoingOrderDao: OngoingOrderDao
    abstract val phoneDao: PhoneDao
    abstract val paymentMethodDao: PaymentMethodDao
    abstract val salesDao: SalesDao
    abstract val successfulOrderDao: SuccessfulOrderDao
    abstract val successfulSaleDao: SuccessfulSaleDao
    abstract val successfulSaleAndDeliveryDao: SuccessfulSaleAndDeliveryDao

    companion object {
        @Volatile
        private var INSTANCE: CrowingRoosterDataBase? = null

        public fun getInstance(context: Context): CrowingRoosterDataBase {
            synchronized(this) {
                var instance: CrowingRoosterDataBase? = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CrowingRoosterDataBase::class.java,
                        "crowing_rooster_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}