package com.cccm.crowingrooster.screens.chat

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ChatViewModelFactory(
    private val firebaseDatabase: FirebaseDatabase,
    private val FirebaseAuth: FirebaseAuth,
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(firebaseDatabase,FirebaseAuth, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}