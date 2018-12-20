package com.igor.bykov.skyscannerapp.data.flight.model

import com.google.gson.annotations.SerializedName

data class Place(
        @SerializedName("Id")
        val id: Long,
        @SerializedName("Code")
        val code: String
)