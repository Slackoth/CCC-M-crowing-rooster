package com.cccm.crowingrooster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cccm.crowingrooster.database.entities.PaymentMethod

@Dao
interface PaymentMethodDao {
    @Insert
    fun insertPaymentMethod(paymentMethod: PaymentMethod)
}