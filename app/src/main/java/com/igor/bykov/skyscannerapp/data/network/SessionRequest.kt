package com.igor.bykov.skyscannerapp.data.network

data class SessionRequest(
        val country: String = "UK",
        val locale: String = "en-GB",
        val locationSchema: String = "iata",
        val originplace: String = "EDI-sky",
        val destinationplace: String = "LHR-sky",
        val outbounddate: String = "2019-05-30",
        val inbounddate: String = "2019-06-30",
        val adults: Int = 1,
        val children: Int = 0,
        val infants: Int = 0,
        val apikey: String = ""
)