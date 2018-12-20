package com.igor.bykov.skyscannerapp.data.network

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
          @Field("outbounddate") outbounddate: String = "2019-05-30",
          @Field("inbounddate") inbounddate: String = "2019-06-30",
          @Field("adults") adults: Int = 1,
          @Field("stops") stops: Int = 0,
          @Field("children") children: Int = 0,
          @Field("infants") infants: Int = 0,
          @Field("apikey") apikey: String = "ss630745725358065467897349852985"
  ): Call<Any>

  @GET("/apiservices/pricing/v1.0/{SessionKey}")
  fun fetchFlight(@Path("SessionKey") sessionKey: String,
      @Query("apiKey") apiKey: String = "ss630745725358065467897349852985",
      @Query("pageIndex") pageIndex: Int,
      @Query("pageSize") pageSize: String = "10"
  ): Deferred<FlightResponse>

}