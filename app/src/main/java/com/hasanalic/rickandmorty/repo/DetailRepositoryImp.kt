package com.hasanalic.rickandmorty.repo

import com.hasanalic.rickandmorty.api.RetrofitAPI
import com.hasanalic.rickandmorty.util.Resource
import com.hasanalic.rickandmorty.model.Character
import javax.inject.Inject

class DetailRepositoryImp @Inject constructor(
    private val retrofitAPI: RetrofitAPI
): DetailRepository {

    override suspend fun getCharacter(characterId: Int): Resource<Character> {
        return try {
            val response = retrofitAPI.getSingleCharacter(characterId)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Null",null)
            } else {
                return Resource.error("Error",null)
            }
        } catch (e: Exception) {
            return Resource.error(e.localizedMessage?:"Error",null)
        }
    }
}