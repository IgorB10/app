package com.igor.bykov.skyscannerapp.domain.flight.repository

import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call

interface SkyScannerFlightRepository {

  suspend fun createSession() : Call<Any>

  suspend fun fetchFlights(pageIdex: Int) : Deferred<FlightResponse>
}