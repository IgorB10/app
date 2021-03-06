package com.igor.bykov.skyscannerapp.data.flight

import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import com.igor.bykov.skyscannerapp.data.network.SkyScannerService
import com.igor.bykov.skyscannerapp.domain.flight.repository.SkyScannerFlightRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class SkyScannerFlightDataRepository(private val service: SkyScannerService) : SkyScannerFlightRepository {

  private var sessionKey: String? = null

  override suspend fun createSession(outbounddate: String, inbounddate: String): String {
    if(sessionKey.isNullOrBlank()) {
      val session = service.createSession(outbounddate = outbounddate, inbounddate = inbounddate).execute()
      val path = session.headers()["location"]?.split("/") ?: Collections.emptyList()
      sessionKey = path[path.size - 1]
    }
    return sessionKey!!
  }

  override suspend fun fetchFlights(outbounddate: String, inbounddate: String, pageIdex: Int): Deferred<FlightResponse> {
    return service.fetchFlight(pageIndex = pageIdex, sessionKey = createSession(outbounddate, inbounddate))
  }
}