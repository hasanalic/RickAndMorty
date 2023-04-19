package com.hasanalic.rickandmorty.repo

import com.hasanalic.rickandmorty.model.Character
import com.hasanalic.rickandmorty.util.Resource

interface DetailRepository {

    suspend fun getCharacter(characterId: Int): Resource<Character>
}