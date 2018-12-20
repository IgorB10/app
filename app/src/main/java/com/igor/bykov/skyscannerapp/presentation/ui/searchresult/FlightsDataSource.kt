package com.igor.bykov.skyscannerapp.presentation.ui.searchresult

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.igor.bykov.skyscannerapp.data.flight.model.Leg
import com.igor.bykov.skyscannerapp.domain.flight.interactor.GetFlight
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.mapper.FlightViewModelMapper
import com.igor.bykov.skyscannerapp.presentation.ui.searchresult.model.FlightViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class FlightsDataSource(private val getFlight: GetFlight, private val scope: CoroutineScope) : PageKeyedDataSource<Int, FlightViewModel>() {

  var state: MutableLiveData<State> = MutableLiveData()
  private var pageIndex = 0

  override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, FlightViewModel>) {
    updateState(State.LOADING)
    try {
      scope.launch {
        val flightViewModes = withContext(Dispatchers.IO) {
          val flight = getFlight.buildUseCaseObservable(pageIndex).await()
          val flightViewModes = FlightViewModelMapper.map(flight)
          flightViewModes
        }
        pageIndex += flightViewModes.size
        callback.onResult(flightViewModes, null, pageIndex)
        updateState(State.DONE)
      }
    } catch (e: Exception) {
      Timber.e(e)
      updateState(State.ERROR)
    }

  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, FlightViewModel>) {
    updateState(State.LOADING)
    try {
      scope.launch {
        val flightViewModes = withContext(Dispatchers.IO) {
          val flight = getFlight.buildUseCaseObservable(pageIndex).await()
          val flightViewModes = FlightViewModelMapper.map(flight)
          flightViewModes
        }
        pageIndex += flightViewModes.size
        callback.onResult(flightViewModes, pageIndex)
        updateState(State.DONE)
      }
    } catch (e: Exception) {
      Timber.e(e)
      updateState(State.ERROR)
    }
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, FlightViewModel>) {}

  private fun updateState(state: State) {
    this.state.postValue(state)
  }
}