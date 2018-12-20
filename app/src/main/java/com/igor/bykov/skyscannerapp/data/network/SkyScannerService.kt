package com.igor.bykov.skyscannerapp.data.network

import com.igor.bykov.skyscannerapp.BuildConfig
import com.igor.bykov.skyscannerapp.data.flight.model.FlightResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

interface SkyScannerService {

  @FormUrlEncoded
  @POST("/apiservices/pricing/v1.0/")
  fun createSession(
          @Field("country") country: String = "UK",
          @Field("locale") locale: String = "en-GB",
          @Field("currency") currency: String = "GBP",
          @Field("originplace") originplace: String = "EDI-sky",
          @Field("destinationplace") destinationplace: String = "LHR-sky",
          @Field("outbounddate") outbounddate: String,
          @Field("inbounddate") inbounddate: String,
          @Field("adults") adults: Int = 1,
//          @Field("stops") stops: Int = 0,
          @Field("children") children: Int = 0,
          @Field("infants") infants: Int = 0,
          @Field("apikey") apikey: String = BuildConfig.API_KEY
  ): Call<Any>

  @GET("/apiservices/pricing/v1.0/{SessionKey}")
  fun fetchFlight(@Path("SessionKey") sessionKey: String,
      @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
      @Query("pageIndex") pageIndex: Int,
      @Query("pageSize") pageSize: String = "10"
  ): Deferred<FlightResponse>

}