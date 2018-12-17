package com.igor.bykov.skyscannerapp.presentation.ui.searchresult

import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import com.igor.bykov.skyscannerapp.presentation.mvp.MvpView

interface SearchResultView : MvpView {

  fun renderFlights(flights: FlightResponse)
}