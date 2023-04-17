package com.hasanalic.rickandmorty.model

data class LocationInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)