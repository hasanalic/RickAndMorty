package com.hasanalic.rickandmorty.model

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("info")
    val info: LocationInfo,
    @SerializedName("results")
    val locations: List<Location>
)