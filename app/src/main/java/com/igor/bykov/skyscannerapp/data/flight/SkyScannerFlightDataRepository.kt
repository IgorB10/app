package com.igor.bykov.skyscannerapp.data.flight

import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import com.igor.bykov.skyscannerapp.data.flight.model.SessionResponse
import com.igor.bykov.skyscannerapp.data.network.SessionRequest
import com.igor.bykov.skyscannerapp.data.network.SkyScannerService
import com.igor.bykov.skyscannerapp.domain.flight.repository.SkyScannerFlightRepository
import kotlinx.coroutines.Deferred
import retrofit2.Call
import java.util.*

class SkyScannerFlightDataRepository(private val service: SkyScannerService) : SkyScannerFlightRepository {

  override suspend fun createSession(): Deferred<Call<SessionResponse>> {
    return service.createSession()
  }

  override suspend fun fetchFlights(): Deferred<FlightResponse> {
    val session = createSession().await().execute()

    val path = session.headers()["location"]?.split("/") ?: Collections.emptyList()

    return service.fetchFlight(sessionKey = path[path.size - 1])
  }
}