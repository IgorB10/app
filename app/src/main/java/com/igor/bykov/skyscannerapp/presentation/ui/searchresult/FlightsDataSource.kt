package com.igor.bykov.skyscannerapp.presentation.ui.searchresult

import androidx.paging.PageKeyedDataSource
import com.igor.bykov.skyscannerapp.data.flight.model.Flight
import com.igor.bykov.skyscannerapp.domain.flight.interactor.GetFlight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FlightsDataSource(val getFlight: GetFlight, val scope: CoroutineScope) : PageKeyedDataSource<Long, Flight>() {

  override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Flight>) {
    scope.launch {
      val flight = withContext(Dispatchers.IO) { getFlight.buildUseCaseObservable(1) }.await()
      callback.onResult(flight.items, null, flight.items.size.toLong())
    }

  }

  override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Flight>) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Flight>) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}