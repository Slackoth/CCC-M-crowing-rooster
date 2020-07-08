package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cccm.crowingrooster.database.entities.SaleDetails

//import com.cccm.crowingrooster.database.entities.SuccessfulSaleDetailsAndSaleMiniOrders

@Dao
interface SaleDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(saleDetails: SaleDetails)

    @Query("SELECT * FROM successful_sale_details WHERE code = :code")
    fun getSpecific(code:String): LiveData<SaleDetails>

//    @Transaction
//    @Query("SELECT * FROM successful_sale_details WHERE code = :code")
//    fun getSuccessfulSaleDetails(code: String): LiveData<SuccessfulSaleDetailsAndSaleMiniOrders>


}