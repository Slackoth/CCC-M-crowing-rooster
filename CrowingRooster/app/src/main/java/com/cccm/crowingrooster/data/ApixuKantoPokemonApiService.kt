package com.cccm.crowingrooster.data

import com.cccm.crowingrooster.data.network.ConnectivityInterceptor
import com.cccm.crowingrooster.data.network.response.KantoPokemon
import com.cccm.crowingrooster.internal.NoConnectivityException
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//TODO: In case the API uses a key
const val API_KEY = "SomeApiKey"

interface ApixuKantoPokemonApiService {
    @GET("pokemon")
    suspend fun getAllAsync(
        @Query("limit") limit: Int = 151
    ): KantoPokemon

    //Like static methods
    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApixuKantoPokemonApiService {
//            ApixuKantoPokemonApiService.create()
//            ApixuKantoPokemonApiService.invoke()
//            ApixuKantoPokemonApiService()
            //TODO: In case the API uses a key
//            val requestInteceptor = Interceptor { chain ->
//                val url = chain.request()
//                    .url()
//                    .newBuilder()
//                    .addQueryParameter("key", API_KEY)
//                    .build()
//                val request = chain.request()
//                    .newBuilder()
//                    .url(url)
//                    .build()
//                return@Interceptor chain.proceed(request)
//            }
            val okHttpClient = OkHttpClient.Builder()
                //.addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuKantoPokemonApiService::class.java)
        }
    }
}