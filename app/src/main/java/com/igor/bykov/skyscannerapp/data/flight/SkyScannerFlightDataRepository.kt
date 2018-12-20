package com.igor.bykov.skyscannerapp.data.flight

import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import com.igor.bykov.skyscannerapp.data.network.SkyScannerService
import com.igor.bykov.skyscannerapp.domain.flight.repository.SkyScannerFlightRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.util.*

class SkyScannerFlightDataRepository(private val service: SkyScannerService) : SkyScannerFlightRepository {

  override suspend fun createSession(): Call<Any> {
    return service.createSession()
  }

  override suspend fun fetchFlights(pageIdex: Int): Deferred<FlightResponse> {
    val session = withContext(Dispatchers.IO) { createSession().execute() }
    val path = session.headers()["location"]?.split("/") ?: Collections.emptyList()
    return service.fetchFlight(pageIndex = pageIdex, sessionKey = path[path.size - 1])
  }
}