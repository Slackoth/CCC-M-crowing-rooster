package com.cccm.crowingrooster.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "successful_sale_details")
data class SaleDetails(
    @PrimaryKey
    @Json(name = "vendedor_codigo")
    var code: String = "",
    @Json(name = "nombre")
    var name: String = "",
    var email: String = "",
    @Json(name = "nombre_empresa")
    var company: String = "",
    @Json(name = "precio")
    var price: String = "",
    @Json(name = "metodo")
    var payment: String = "",
    @Json(name = "fecha")
    var date: String = "",
    @Json(name = "cantidad_total")
    @ColumnInfo(name = "total_quantity")
    var totalQuantity: Int = 0,
    @Json(name = "pedidos")
    @Ignore var miniorder: List<SaleMiniOrders> = listOf()
) {
//    constructor(
//        code: String,
//        name: String,
//        email: String,
//        company: String,
//        price: String,
//        payment: String,
//        confirmedDate: String,
//        totalQuantity: Int
//    ) : this(code, name, email, company, price, payment, confirmedDate, totalQuantity, listOf())

    constructor() : this("","","","","","","",0, listOf())
}