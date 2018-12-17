package com.igor.bykov.skyscannerapp.presentation.ui.searchresult

import com.igor.bykov.skyscannerapp.domain.flight.interactor.GetFlight
import com.igor.bykov.skyscannerapp.presentation.mvp.BasePresenter
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchResultPresenter(val getFlight: GetFlight) : BasePresenter<SearchResultView>() {

  fun fetchFlights() {
    launch {

      try {
        val flights = getFlight.buildUseCaseObservable(Unit).await()

        view.renderFlights(flights)
      } catch (e: Exception) {
        Timber.e(e)
      }

    }
  }

}