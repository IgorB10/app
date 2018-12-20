package com.igor.bykov.skyscannerapp.data.flight.model

import com.google.gson.annotations.SerializedName

data class Carriers(
        @SerializedName("Id")
        val id: Long,
        @SerializedName("Code")
        val code: String,
        @SerializedName("Name")
        val name: String,
        @SerializedName("ImageUrl")
        val ImageUrl: String
)