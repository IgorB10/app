package com.igor.bykov.skyscannerapp.data.flight.model

import com.google.gson.annotations.SerializedName

data class Leg(
        @SerializedName("Id")
        val id: String,
        @SerializedName("Departure")
        val departure: String,
        @SerializedName("Arrival")
        val arrival: String,
        @SerializedName("Carriers")
        val carriers: List<Long>,
        @SerializedName("Duration")
        val duration: Long,
        @SerializedName("OriginStation")
        val originStation: Long,
        @SerializedName("DestinationStation")
        val destinationStation: Long
)