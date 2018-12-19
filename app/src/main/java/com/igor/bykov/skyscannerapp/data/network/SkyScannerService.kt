package com.igor.bykov.skyscannerapp.data.network

import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import com.igor.bykov.skyscannerapp.data.flight.model.SessionResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

interface SkyScannerService {

  @FormUrlEncoded
  @POST("/apiservices/pricing/v1.0/")
  fun createSession(
          @Field ("country") country: String = "UK",
          @Field ("locale") locale: String = "en-GB",
          @Field ("currency") currency: String = "GBP",
          @Field ("originplace") originplace: String = "EDI-sky",
          @Field ("destinationplace") destinationplace: String = "LHR-sky",
          @Field ("outbounddate") outbounddate: String = "2019-05-30",
          @Field ("inbounddate") inbounddate: String = "2019-06-30",
          @Field ("adults") adults: Int = 1,
          @Field ("children") children: Int = 0,
          @Field ("infants") infants: Int = 0,
          @Field ("apikey") apikey: String = ""
  ): Deferred<Call<SessionResponse>>

  @FormUrlEncoded
  @Headers("Content-Type: application/x-www-form-urlencoded")
  @GET("/apiservices/pricing/v1.0/{SessionKey}")
  fun fetchFlight(@Path("SessionKey") sessionKey: String, @Query("apiKey") apiKey: String = ""): Deferred<FlightResponse>

}