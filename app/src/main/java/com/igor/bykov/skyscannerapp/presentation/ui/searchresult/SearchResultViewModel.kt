package com.igor.bykov.skyscannerapp.presentation.ui.searchresult

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.igor.bykov.skyscannerapp.data.flight.model.Flight
import com.igor.bykov.skyscannerapp.domain.flight.interactor.GetFlight
import com.igor.bykov.skyscannerapp.presentation.mvvm.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchResultViewModel(private val getFlight: GetFlight) : BaseViewModel() {

  var flightList: LiveData<PagedList<Flight>>? = null

  fun fetchFlights() {
    launch {

      try {
        val flights = getFlight.buildUseCaseObservable(Unit).await()

      } catch (e: Exception) {
        Timber.e(e)
      }

    }
  }

}