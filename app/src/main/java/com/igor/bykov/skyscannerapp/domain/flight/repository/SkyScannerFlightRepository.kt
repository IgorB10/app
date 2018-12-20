package com.igor.bykov.skyscannerapp.domain.flight.repository

import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call

interface SkyScannerFlightRepository {

  suspend fun createSession(outbounddate: String, inbounddate: String) : String

  suspend fun fetchFlights(outbounddate: String, inbounddate: String, pageIdex: Int) : Deferred<FlightResponse>
}