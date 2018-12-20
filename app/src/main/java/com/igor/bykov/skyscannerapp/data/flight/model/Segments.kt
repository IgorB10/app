package com.igor.bykov.skyscannerapp.data.flight.model

import com.google.gson.annotations.SerializedName

data class Segments(
        val id: Long,
        @SerializedName("Carrier")
        val carrier: Long,
        @SerializedName("DepartureDateTime")
        val departureDate: String,
        @SerializedName("ArrivalDateTime")
        val arrivalDateT: String
)