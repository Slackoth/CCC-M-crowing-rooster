package com.cccm.crowingrooster.screens.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cccm.crowingrooster.network.repository.product.BatteryRepository
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ProductViewModel(
    BatteryRepository  : BatteryRepository,
    app: Application
): AndroidViewModel(app) {
    val battery = BatteryRepository.getSpecific(2)
}
