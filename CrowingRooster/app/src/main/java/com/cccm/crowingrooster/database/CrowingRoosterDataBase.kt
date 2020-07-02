package com.cccm.crowingrooster.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cccm.crowingrooster.database.daos.SellerClientDao
import com.cccm.crowingrooster.database.entities.SellerClient


@Database(entities = [SellerClient::class],version = 12,exportSchema = false)
abstract class CrowingRoosterDataBase: RoomDatabase() {

    abstract val sellerClientDao: SellerClientDao

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
                "sleep_history_database"
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}
