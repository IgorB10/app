package com.igor.bykov.skyscannerapp.data.network

import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import com.igor.bykov.skyscannerapp.data.flight.model.SessionResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SkyScannerService {

  @POST("/apiservices/pricing/v1.0/?" +
//      "country=UK\n" +
//      "    &currency=GBP\n" +
//      "    &locale=en-GB\n" +
//      "    &locationSchema=iata\n" +
//      "    &originplace=EDI\n" +
//      "    &destinationplace=LHR\n" +
//      "    &outbounddate=2017-05-30\n" +
//      "    &inbounddate=2017-06-02\n" +
//      "    &adults=1\n" +
//      "    &children=0\n" +
//      "    &infants=0\n" +
      "apikey=ss630745725358065467897349852985")
  fun createSession(): Deferred<Call<SessionResponse>>

  @GET("/apiservices/pricing/v1.0/")
  fun fetchFlight(@Query("apiKey") apiKey: String): Deferred<FlightResponse>

}