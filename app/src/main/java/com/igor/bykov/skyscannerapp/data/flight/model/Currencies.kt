package com.igor.bykov.skyscannerapp.data.flight.model

import com.google.gson.annotations.SerializedName

data class Currencies(
        @SerializedName("Code")
        val code: String,
        @SerializedName("Symbol")
        val symbol: String
)