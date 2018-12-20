package com.igor.bykov.skyscannerapp.presentation.ui.searchresult.model

data class FlightDetailsViewModel(
        val time: String,
        val stops: String,
        val carrierName: String? = "",
        val carrierLogoUrl: String? = "",
        val duration: String
)