package com.cccm.crowingrooster.screens.login


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cccm.crowingrooster.database.entities.User
import com.cccm.crowingrooster.network.repository.login.LogInRepository

class LogInViewModel(
    private val logInRepository: LogInRepository,
    private val app: Application
): AndroidViewModel(app) {

    fun getSpecific(email: String): User {
        return logInRepository.getAll(email)
    }
    fun setUser() {
        logInRepository.setUser()
    }


}