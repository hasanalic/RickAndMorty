package com.hasanalic.rickandmorty.api

import com.hasanalic.rickandmorty.model.Location
import com.hasanalic.rickandmorty.model.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("location")
    suspend fun getLocations(): Response<LocationResponse>

    @GET("location")
    suspend fun getNextLocationPage(@Query("page") page: Int): Response<LocationResponse>

    @GET("location/{id}")
    suspend fun getSingleLocation(@Path("id") id: Int): Response<Location>

    @GET("character/{id}")
    suspend fun getSingleCharacter(@Path("id") id: Int): Response<com.hasanalic.rickandmorty.model.Character>

    @GET("character/{ids}")
    suspend fun getMultipleCharacters(@Path("ids") ids: String): Response<List<com.hasanalic.rickandmorty.model.Character>>
}