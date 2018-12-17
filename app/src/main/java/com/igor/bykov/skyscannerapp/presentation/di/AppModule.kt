package com.igor.bykov.skyscannerapp.presentation.di

import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.SearchResultPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

object AppModule {

  val module = Kodein.Module("AppModule") {

    bind<SearchResultPresenter>() with provider {
      SearchResultPresenter(instance())
    }
  }
}