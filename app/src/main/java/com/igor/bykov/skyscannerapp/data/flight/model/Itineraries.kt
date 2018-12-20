package com.igor.bykov.skyscannerapp.data.flight.model

import com.google.gson.annotations.SerializedName

data class Itineraries(
        @SerializedName("OutboundLegId")
        val outboundLegId: String,
        @SerializedName("InboundLegId")
        val inboundLegId: String,
        @SerializedName("PricingOptions")
        val price: List<PricingOptions>
)