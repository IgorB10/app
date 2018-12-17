package com.igor.bykov.skyscannerapp.domain.flight.repository

import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import com.igor.bykov.skyscannerapp.data.flight.model.SessionResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call

interface SkyScannerFlightRepository {

  suspend fun createSession() : Deferred<Call<SessionResponse>>

  suspend fun fetchFlights() : Deferred<FlightResponse>
}