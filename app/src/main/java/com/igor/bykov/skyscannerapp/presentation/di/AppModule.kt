package com.igor.bykov.skyscannerapp.presentation.di

import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.SearchResultViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

object AppModule {

  val module = Kodein.Module("AppModule") {

    bind<SearchResultViewModel>() with provider {
      SearchResultViewModel(instance())
    }
  }
}