package com.igor.bykov.skyscannerapp.domain.flight.interactor

import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import com.igor.bykov.skyscannerapp.domain.UseCase
import com.igor.bykov.skyscannerapp.domain.flight.repository.SkyScannerFlightRepository
import kotlinx.coroutines.Deferred

class GetFlight(private val repository : SkyScannerFlightRepository) : UseCase<FlightResponse, Unit>() {

  override suspend fun buildUseCaseObservable(params: Unit): Deferred<FlightResponse> {
    return repository.fetchFlights()
  }
}