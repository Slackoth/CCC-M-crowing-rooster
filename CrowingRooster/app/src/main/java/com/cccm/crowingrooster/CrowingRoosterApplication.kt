package com.cccm.crowingrooster

import android.app.Application
import android.util.Log
import com.jakewharton.threetenabp.AndroidThreeTen

class CrowingRoosterApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}