package com.cccm.crowingrooster.network


import com.cccm.crowingrooster.database.entities.SaleDetails
import com.cccm.crowingrooster.database.entities.SalePreview
import com.cccm.crowingrooster.database.entities.Battery
import com.cccm.crowingrooster.database.entities.Seller
import com.cccm.crowingrooster.database.entities.SellerClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://192.168.0.11:3000/"
    //"http://192.168.1.5:3000/"

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
        @Query("codigo") codigo: String
    ): List<SellerClient>

        @GET("seller/specific")
    suspend fun getSellerAsync(
        @Query("codigo") codigo: String
    ): List<Seller>


    @GET("salepreview/successful")
    suspend fun getSuccessfulSalePreviewAsync(
        @Query("codigo") codigo: String
        //@Query("estado") estado: String
    ): List<SalePreview>

    @GET("salepreview/ongoing")
    suspend fun getOngoingSalePreviewAsync(
        @Query("codigo") codigo: String
        //@Query("estado") estado: String
    ): List<SalePreview>

    @GET("saledetails/successful")
    suspend fun getSuccessfulSaleDetailsAsync(
        @Query("codigo") codigo: String,
        @Query("ordenId") ordenId: String
    ): List<SaleDetails>

    @GET("saledetails/ongoing")
    suspend fun getOngoingSaleDetailsAsync(
        @Query("codigo") codigo: String,
        @Query("ordenId") ordenId: String
    ): List<SaleDetails>



    //product

    @GET("product/specific")
    suspend fun getProductAsync(
        @Query("codigo") id:Int
    ): List<Battery>

    @GET("product/info")
    suspend fun getProductInfoAsync(
        @Query("codigo") id:Int
    ): List<Battery>



    object CrowingRoosterApi {
        val retrofitService: CrowingRoosterApiService by lazy {
            retrofit.create(CrowingRoosterApiService::class.java)
        }
    }
}