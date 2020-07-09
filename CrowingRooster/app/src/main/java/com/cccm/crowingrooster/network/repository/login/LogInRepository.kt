package com.cccm.crowingrooster.network.repository.login

import android.util.Log
import com.cccm.crowingrooster.database.daos.UserDao
import com.cccm.crowingrooster.database.entities.User
import com.cccm.crowingrooster.network.CrowingRoosterApiService
import com.cccm.crowingrooster.network.repository.seller.ConfirmSaleRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LogInRepository(private val userDao: UserDao) {

    fun getAll(email: String): User {
        return userDao.getUser(email)
    }

    fun setUser() {
        GlobalScope.launch {
            try {
                val users = CrowingRoosterApiService.CrowingRoosterApi
                    .retrofitService.verifyUser()
                Log.d("users","$users")
                for (user in users ) {
                    Log.d("user","$user")
                    userDao.insert(user)
                }
            }
            catch (e: Exception) {
                Log.d("Connection","No connection: ${e.message}")
            }
        }
    }

    companion object {
        private var INSTANCE: LogInRepository? = null

        fun getInstance(userDao: UserDao) = INSTANCE ?: createInstance(userDao)
            .also { INSTANCE = it }

        private fun createInstance(userDao: UserDao) = LogInRepository(userDao)
    }

}