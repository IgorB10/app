package com.igor.bykov.skyscannerapp.presentation.ui.searchresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igor.bykov.skyscannerapp.domain.flight.interactor.GetFlight
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SearchResultViewModelFactory(kodein: Kodein) : ViewModelProvider.Factory {

  private val getFlight: GetFlight by kodein.instance()

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return SearchResultViewModel(getFlight) as T
  }
}