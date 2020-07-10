package com.cccm.crowingrooster.network

import com.cccm.crowingrooster.database.entities.*

import com.cccm.crowingrooster.database.entities.*

import com.cccm.crowingrooster.database.entities.*
import com.cccm.crowingrooster.database.entities.order.OrderPreview
import com.cccm.crowingrooster.network.body.ConfirmSaleBody
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val BASE_URL = "http://192.168.1.4:3000/"
    //"http://192.168.1.22:3000/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CrowingRoosterApiService {
    @GET("sellerclient/all")
    suspend fun getAllSellerClientAsync(
        @Query("codigo") codigo: String?
    ): List<SellerClient>

        @GET("seller/specific")
    suspend fun getSellerAsync(
        @Query("codigo") codigo: String?
    ): List<Seller>

    @GET("salepreview/successful")
    suspend fun getSuccessfulSalePreviewAsync(
        @Query("codigo") codigo: String?
        //@Query("estado") estado: String
    ): List<SalePreview>

    @GET("salepreview/ongoing")
    suspend fun getOngoingSalePreviewAsync(
        @Query("codigo") codigo: String?
        //@Query("estado") estado: String
    ): List<SalePreview>

    @GET("saledetails/successful")
    suspend fun getSuccessfulSaleDetailsAsync(
        @Query("codigo") codigo: String?,
        @Query("ordenId") ordenId: String?
    ): List<SaleDetails>

    @GET("saledetails/ongoing")
    suspend fun getOngoingSaleDetailsAsync(
        @Query("codigo") codigo: String?,
        @Query("ordenId") ordenId: String?
    ): List<SaleDetails>

    @GET("deliverypreview/ongoing")
    suspend fun getOngoingDeliveryPreviewAsync(
        @Query("codigo") codigo: String
    ): List<DeliveryPreview>

    @GET("deliverypreview/successful")
    suspend fun getSuccessfulDeliveryPreviewAsync(
        @Query("codigo") codigo: String
    ): List<DeliveryPreview>

    @GET("product/specific")
    suspend fun getProductAsync(
        @Query("codigo") id:Int
    ): List<Battery>

    @GET("product/info")
    suspend fun getProductInfoAsync(
        @Query("codigo") model:String
    ): List<BatteryInfo>


//    //pedido
//    @POST("pedido/insert")
//        suspend fun sendInsertPedido(@Body PedidoBody:PedidoBody)
//


    @POST("confirmsale")
    suspend fun sendConfirmSale(
        @Body confirmSaleBody: ConfirmSaleBody,
        @Query("ventaId") ventaId: String?
    )

    @GET("users")
    suspend fun verifyUser(
        //@Query("email") email: String
    ): List<User>

    @GET("orderpreview/successful")
    suspend fun getSuccessfulOrderPreviewAsync(
        @Query("codigo") codigo: String?
    ): List<OrderPreview>

    @GET("orderpreview/ongoing")
    suspend fun getOngoingOrderPreviewAsync(
        @Query("codigo") codigo: String?
    ): List<OrderPreview>

    @GET("orderpreview/canceled")
    suspend fun getCanceledOrderPreviewAsync(
        @Query("codigo") codigo: String?
    ): List<OrderPreview>

    @GET("catalogue")
    suspend fun getBatteriesCatalogue(
        @Query("codigo") model: String
    ): List<BatteryInfo>

    object CrowingRoosterApi {
        val retrofitService: CrowingRoosterApiService by lazy {
            retrofit.create(CrowingRoosterApiService::class.java)
        }
    }
}