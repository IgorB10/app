package com.igor.bykov.skyscannerapp.domain.flight.interactor

import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import com.igor.bykov.skyscannerapp.domain.UseCase
import com.igor.bykov.skyscannerapp.domain.flight.repository.SkyScannerFlightRepository
import kotlinx.coroutines.Deferred
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

class GetFlight(private val repository: SkyScannerFlightRepository) : UseCase<FlightResponse, Int>() {

  private val dayFormat = "YYYY-MM-dd"

  override suspend fun buildUseCaseObservable(params: Int): Deferred<FlightResponse> {
    val today = LocalDate.now()
    val old = today.dayOfWeek
    var sunday = 0

    if (sunday <= old) {
      sunday += 7
    }
    val next = today.plusDays(sunday - old)
    val nextWeek = next.plusWeeks(1)
    return repository.fetchFlights(outbounddate = next.toString(dayFormat), inbounddate = nextWeek.toString(dayFormat), pageIdex = params)
  }

}