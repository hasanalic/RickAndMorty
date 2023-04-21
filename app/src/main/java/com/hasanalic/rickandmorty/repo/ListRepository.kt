package com.hasanalic.rickandmorty.repo

import com.hasanalic.rickandmorty.model.Location
import com.hasanalic.rickandmorty.model.LocationResponse
import com.hasanalic.rickandmorty.util.Resource

interface ListRepository {

    suspend fun getLocationResponse(): Resource<LocationResponse>
    suspend fun getNextLocationPage(page: Int): Resource<LocationResponse>
    suspend fun getSingleLocation(id: Int): Resource<Location>
    suspend fun getMultipleCharacters(ids: String): Resource<List<com.hasanalic.rickandmorty.model.Character>>
}