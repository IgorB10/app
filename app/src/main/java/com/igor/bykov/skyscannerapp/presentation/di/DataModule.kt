package com.igor.bykov.skyscannerapp.presentation.di

import com.igor.bykov.skyscannerapp.data.flight.SkyScannerFlightDataRepository
import com.igor.bykov.skyscannerapp.domain.flight.interactor.GetFlight
import com.igor.bykov.skyscannerapp.domain.flight.repository.SkyScannerFlightRepository
import org.kodein.di.Kodein
import org.kodein.di.android.AndroidComponentsWeakScope
import org.kodein.di.generic.*

object DataModule {

  val module = Kodein.Module("DataModule") {

    bind<SkyScannerFlightRepository>() with scoped(AndroidComponentsWeakScope).singleton { SkyScannerFlightDataRepository(instance()) }

    bind<GetFlight>() with provider {
      GetFlight(instance())
    }
  }
}