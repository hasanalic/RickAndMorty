package com.hasanalic.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasanalic.rickandmorty.repo.DetailRepository
import com.hasanalic.rickandmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository
): ViewModel() {

    private var _character = MutableLiveData<Resource<com.hasanalic.rickandmorty.model.Character>>()
    val character: LiveData<Resource<com.hasanalic.rickandmorty.model.Character>>
        get() = _character

    fun getCharacter(characterId: Int) {
        _character.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getCharacter(characterId)
            _character.value = response
        }
    }
}