package com.igor.bykov.skyscannerapp.presentation.ui.searchresult

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.igor.bykov.skyscannerapp.data.flight.model.Leg
import com.igor.bykov.skyscannerapp.domain.flight.interactor.GetFlight
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.model.FlightViewModel
import kotlinx.coroutines.CoroutineScope

class FlightsDataSourceFactory(private val getFlight: GetFlight, private val scope: CoroutineScope) : DataSource.Factory<Int, FlightViewModel>() {

  val flightDataSourceLiveData = MutableLiveData<FlightsDataSource>()

  override fun create(): PageKeyedDataSource<Int, FlightViewModel> {
    val flightsDataSource = FlightsDataSource(getFlight, scope)
    flightDataSourceLiveData.postValue(flightsDataSource)
    return flightsDataSource
  }
}