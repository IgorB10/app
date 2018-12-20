package com.igor.bykov.skyscannerapp

import android.app.Application
import com.igor.bykov.skyscannerapp.data.di.NetModule
import com.igor.bykov.skyscannerapp.presentation.di.AppModule
import com.igor.bykov.skyscannerapp.presentation.di.DataModule
import net.danlew.android.joda.JodaTimeAndroid
import org.greenrobot.eventbus.EventBus
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import timber.log.Timber

class SkyScannerApplication : Application(), KodeinAware {

  override val kodein: Kodein = Kodein.lazy {
    import(AppModule.module)
    import(NetModule.module)
    import(DataModule.module)
    import(androidModule(this@SkyScannerApplication))
  }

  override fun onCreate() {
    super.onCreate()
    EventBus.builder().addIndex(SkyScannerEventBusIndex()).installDefaultEventBus()
    JodaTimeAndroid.init(this)
    Timber.plant(Timber.DebugTree())
  }
}