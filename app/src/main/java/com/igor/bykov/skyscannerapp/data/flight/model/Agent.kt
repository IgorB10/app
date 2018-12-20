package com.igor.bykov.skyscannerapp.data.flight.model

import com.google.gson.annotations.SerializedName

data class Agent(
        @SerializedName("Id")
        val id: Long,
        @SerializedName("Name")
        val name: String
)