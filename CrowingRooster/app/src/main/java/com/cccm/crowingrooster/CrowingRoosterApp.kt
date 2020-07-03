package com.cccm.crowingrooster

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class CrowingRoosterApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}