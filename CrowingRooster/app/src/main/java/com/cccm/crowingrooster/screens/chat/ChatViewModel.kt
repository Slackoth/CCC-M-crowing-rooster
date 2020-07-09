package com.cccm.crowingrooster.screens.chat

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ChatViewModel(
    var firebaseDatabase: FirebaseDatabase= FirebaseDatabase.getInstance(),
    var firebaseAuth: FirebaseAuth= FirebaseAuth.getInstance(),
    application: Application

){
    init {

    }




}
