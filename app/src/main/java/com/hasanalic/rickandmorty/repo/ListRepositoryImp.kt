package com.hasanalic.rickandmorty.repo

import com.hasanalic.rickandmorty.api.RetrofitAPI
import com.hasanalic.rickandmorty.model.Location
import com.hasanalic.rickandmorty.model.LocationResponse
import com.hasanalic.rickandmorty.util.Resource
import javax.inject.Inject

class ListRepositoryImp @Inject constructor(
    private val retrofitAPI: RetrofitAPI
): ListRepository {

    override suspend fun getLocationResponse(): Resource<LocationResponse> {
        return try {
            val response = retrofitAPI.getLocations()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Null", null)
            } else {
                Resource.error("Error",null)
            }
        } catch (e: Exception) {
            Resource.error(e.localizedMessage?:"No data",null)
        }
    }

    override suspend fun getSingleLocation(id: Int): Resource<Location> {
        return try {
            val response = retrofitAPI.getSingleLocation(id)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Null",null)
            } else {
                Resource.error("Error",null)
            }
        } catch (e: Exception) {
            Resource.error(e.localizedMessage ?: "No data",null)
        }
    }

    override suspend fun getMultipleCharacters(ids: String): Resource<List<com.hasanalic.rickandmorty.model.Character>> {
        return try {
            val response = retrofitAPI.getMultipleCharacters(ids)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Null",null)
            } else {
                Resource.error("Error",null)
            }
        } catch (e: Exception) {
            Resource.error(e.localizedMessage ?: "No data", null)
        }
    }
}