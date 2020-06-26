package com.cccm.crowingrooster

import android.app.Application
import android.content.Context
import com.cccm.crowingrooster.data.ApixuKantoPokemonApiService
import com.cccm.crowingrooster.data.db.PokemonDatabase
import com.cccm.crowingrooster.data.db.dao.PokemonDao
import com.cccm.crowingrooster.data.network.ConnectivityInterceptor
import com.cccm.crowingrooster.data.network.ConnectivityInterceptorImpl
import com.cccm.crowingrooster.data.network.PokemonNetworkDataSource
import com.cccm.crowingrooster.data.network.PokemonNetworkDataSourceImpl
import com.cccm.crowingrooster.data.repository.KantoPokemonRepository
import com.cccm.crowingrooster.data.repository.KantoPokemonRepositoryImpl
import com.cccm.crowingrooster.screens.seller_client_list.SellerClientViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class KantoPokemonApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@KantoPokemonApplication))

        //The instance comes from androiXModule
        bind<PokemonDatabase>() with singleton { PokemonDatabase(instance<Context>()) }
        //bind<PokemonDatabase>() with singleton { PokemonDatabase.invoke(this@KantoPokemonApplication) }
        bind() from singleton { instance<PokemonDatabase>().pokemonDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuKantoPokemonApiService(instance()) }
        bind<PokemonNetworkDataSource>() with singleton { PokemonNetworkDataSourceImpl(instance()) }
        bind<KantoPokemonRepository>() with singleton { KantoPokemonRepositoryImpl(instance(),instance()) }
        bind() from provider { SellerClientViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}