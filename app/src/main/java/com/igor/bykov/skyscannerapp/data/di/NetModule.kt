package com.igor.bykov.skyscannerapp.data.di

import com.igor.bykov.skyscannerapp.data.network.SkyScannerService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetModule {

  val module = Kodein.Module("NetModule") {

    bind<OkHttpClient>() with singleton {
      OkHttpClient.Builder()
          .addInterceptor(instance<HttpLoggingInterceptor>())
          .build()
    }

    bind<Retrofit>() with singleton {
      Retrofit.Builder()
          .baseUrl("http://partners.api.skyscanner.net")
          .addCallAdapterFactory(CoroutineCallAdapterFactory())
          .addConverterFactory(GsonConverterFactory.create())
          .client(instance())
          .build()
    }

    bind<SkyScannerService>() with singleton {
      instance<Retrofit>().create(SkyScannerService::class.java)
    }

    bind<HttpLoggingInterceptor>() with singleton {
      val logging = HttpLoggingInterceptor()
      logging.level = HttpLoggingInterceptor.Level.BODY
      logging
    }
  }
}