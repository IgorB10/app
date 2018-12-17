package com.igor.bykov.skyscannerapp.data.flight.model

import com.google.gson.annotations.SerializedName

data class Flight(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val url: String
)