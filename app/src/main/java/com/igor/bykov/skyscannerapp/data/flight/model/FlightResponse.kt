package com.igor.bykov.skyscannerapp.data.flight.model

data class FlightResponse(
    val total_count: Long,
    val items: List<Flight>
)