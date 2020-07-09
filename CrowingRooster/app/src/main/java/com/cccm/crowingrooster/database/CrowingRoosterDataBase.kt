package com.cccm.crowingrooster.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cccm.crowingrooster.database.daos.*
import com.cccm.crowingrooster.database.entities.*


@Database(entities = [SellerClient::class,Seller::class,SalePreview::class, SaleDetails::class,SaleMiniOrders::class,
User::class],version = 20,exportSchema = false)
abstract class CrowingRoosterDataBase: RoomDatabase() {

    abstract val sellerClientDao: SellerClientDao
    abstract val sellerDao: SellerDao
    abstract val salePreviewDao: SalePreviewDao
    abstract val saleDetailsDao: SaleDetailsDao
    abstract val saleMiniOrdersDao: SaleMiniOrdersDao
    abstract val userDao: UserDao

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
