package com.igor.bykov.skyscannerapp.presentation.ui.searchresult

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.igor.bykov.skyscannerapp.domain.flight.interactor.GetFlight
import com.igor.bykov.skyscannerapp.presentation.mvvm.BaseViewModel
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.model.FlightViewModel

class SearchResultViewModel(getFlight: GetFlight) : BaseViewModel() {

  private val pageSize = 10
  var newsList: LiveData<PagedList<FlightViewModel>>
  private val flightsDataSourceFactory: FlightsDataSourceFactory

  init {
    val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
    flightsDataSourceFactory = FlightsDataSourceFactory(getFlight, this)
    newsList = LivePagedListBuilder<Int, FlightViewModel>(flightsDataSourceFactory, config).build()
  }

  fun getState(): LiveData<State> = Transformations.switchMap<FlightsDataSource,
          State>(flightsDataSourceFactory.flightDataSourceLiveData, FlightsDataSource::state)


  fun listIsEmpty(): Boolean {
    return newsList.value?.isEmpty() ?: true
  }
}