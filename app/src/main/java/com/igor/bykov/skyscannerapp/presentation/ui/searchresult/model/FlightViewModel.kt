package com.igor.bykov.skyscannerapp.presentation.ui.searchresult.model

data class FlightViewModel(
        val id: String,
        val firstFlight: FlightDetailsViewModel?,
        val secondFlight: FlightDetailsViewModel?,
        val rating: Int,
        val price: String,
        val operator: String
)