package com.igor.bykov.skyscannerapp.data.flight.model

import com.google.gson.annotations.SerializedName

data class FlightResponse(
        @SerializedName("Itineraries")
        val itineraries: List<Itineraries>,
        @SerializedName("Legs")
        val legs: List<Leg>,
        @SerializedName("Segments")
        val segments: List<Segments>,
        @SerializedName("Carriers")
        val carriers: List<Carriers>,
        @SerializedName("Currencies")
        val currencies: List<Currencies>,
        @SerializedName("Places")
        val places: List<Place>,
        @SerializedName("Agents")
        val agents: List<Agent>

)