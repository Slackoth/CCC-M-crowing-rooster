package com.cccm.crowingrooster.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cccm.crowingrooster.database.daos.*
import com.cccm.crowingrooster.database.daos.SuccessfulSaleAndDeliveryDao
import com.cccm.crowingrooster.database.entities.*
import java.util.concurrent.Executors


@Database(entities = [User::class,Quality::class,Polarity::class,Battery::class,Buyer::class,
    CanceledOrder::class,Company::class,Delivery::class,DeliveryState::class,DeliveryMan::class,
    DeliveryManAndDelivery::class,Seller::class/*,Email::class*/,MiniOrder::class,Order::class,
    OngoingOrder::class,OngoingSale::class,Phone::class,PaymentMethod::class,Sales::class,
    SuccessfulOrder::class,SuccessfulSale::class,SuccessfulSaleAndDelivery::class],version = 10,exportSchema = false)
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

        fun getInstance(context: Context): CrowingRoosterDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = buildDataBase(context)
                    INSTANCE = instance
                    INSTANCE?.populate()
                }

                return instance
            }
        }

        private fun buildDataBase(context: Context): CrowingRoosterDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                CrowingRoosterDataBase::class.java,
                "sleep_history_database"
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                /*.addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Executors.newSingleThreadExecutor().execute {
                            getInstance(context).companyDao.insertCompany(Company(company_name = "Cronora"))
//                            INSTANCE?.companyDao?.insertCompany(Company(company_name = "Microno"))
//                            INSTANCE?.companyDao?.insertCompany(Company(company_name = "Cronoa"))
//                            INSTANCE?.deliveryStateDao?.insertDeliveryState(DeliveryState(state = "Confirmada"))
//                            INSTANCE?.deliveryStateDao?.insertDeliveryState(DeliveryState(state = "Pendiente"))
//                            INSTANCE?.paymentMethodDao?.insertPaymentMethod(PaymentMethod(payment_method = "Efectivo"))
//                            INSTANCE?.paymentMethodDao?.insertPaymentMethod(PaymentMethod(payment_method = "Tarjeta"))
//                            INSTANCE?.polarityDao?.insertPolarity(Polarity(type = "Derecha"))
//                            INSTANCE?.polarityDao?.insertPolarity(Polarity(type = "Izquierda"))
//                            INSTANCE?.qualityDao?.insertQuality(Quality(type = "Azul"))
//                            INSTANCE?.qualityDao?.insertQuality(Quality(type = "Amarilla"))
                        }
                    }
                })*/
                .build()
        }

    }

    private fun populate() {
        Executors.newSingleThreadExecutor().execute {
            if(INSTANCE?.companyDao?.getAll()?.isEmpty()!!) {
                INSTANCE?.companyDao?.insertCompany(Company(company_name = "Cronora"))
                INSTANCE?.companyDao?.insertCompany(Company(company_name = "Microno"))
                INSTANCE?.companyDao?.insertCompany(Company(company_name = "Cronoa"))
            }
            if (INSTANCE?.deliveryStateDao?.getAll()?.isEmpty()!!) {
                INSTANCE?.deliveryStateDao?.insertDeliveryState(DeliveryState(state = "Confirmada"))
                INSTANCE?.deliveryStateDao?.insertDeliveryState(DeliveryState(state = "Pendiente"))
            }
            if(INSTANCE?.paymentMethodDao?.getAll()?.isEmpty()!!) {
                INSTANCE?.paymentMethodDao?.insertPaymentMethod(PaymentMethod(payment_method = "Efectivo"))
                INSTANCE?.paymentMethodDao?.insertPaymentMethod(PaymentMethod(payment_method = "Tarjeta"))
            }
            if(INSTANCE?.polarityDao?.getAll()?.isEmpty()!!) {
                INSTANCE?.polarityDao?.insertPolarity(Polarity(type = "Derecha"))
                INSTANCE?.polarityDao?.insertPolarity(Polarity(type = "Izquierda"))
            }
            if (INSTANCE?.qualityDao?.getAll()?.isEmpty()!!) {
                INSTANCE?.qualityDao?.insertQuality(Quality(type = "Azul"))
                INSTANCE?.qualityDao?.insertQuality(Quality(type = "Amarilla"))
            }
        }
    }
}
