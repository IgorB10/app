package com.igor.bykov.skyscannerapp.data.flight.model

import com.google.gson.annotations.SerializedName

data class PricingOptions(
        @SerializedName("Price")
        val price: Float,

        @SerializedName("DeeplinkUrl")
        val link: String,

        @SerializedName("Agents")
        val agents: List<Long>

)