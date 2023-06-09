package com.hasanalic.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasanalic.rickandmorty.model.Location
import com.hasanalic.rickandmorty.model.LocationResponse
import com.hasanalic.rickandmorty.repo.ListRepository
import com.hasanalic.rickandmorty.util.Constants
import com.hasanalic.rickandmorty.util.Resource
import com.hasanalic.rickandmorty.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: ListRepository
): ViewModel() {

    // Location list
    private var _locations = MutableLiveData<Resource<LocationResponse>>()
    val locations: LiveData<Resource<LocationResponse>>
        get() = _locations

    // Single location
    private var _singleLocation = MutableLiveData<Resource<Int>>()
    val singleLocation: LiveData<Resource<Int>>
        get() = _singleLocation

    // Character list
    private var _characters = MutableLiveData<Resource<List<com.hasanalic.rickandmorty.model.Character>>>()
    val characters: LiveData<Resource<List<com.hasanalic.rickandmorty.model.Character>>>
        get() = _characters

    fun getLocations() {
        _locations.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getLocationResponse()
            _locations.value = response
        }
    }

    fun getNextLocationPage(page: Int) {
        if (page != -1) {
            var currentLocationList = _locations.value?.data?.locations
            _locations.value = Resource.loading(null)
            viewModelScope.launch {
                val response = repository.getNextLocationPage(page)
                val nextPageLocationList = response.data?.locations
                nextPageLocationList?.let {
                    val nextPageLocationArrayList = ArrayList(it)
                    currentLocationList?.let {current ->
                        currentLocationList = current.plus(nextPageLocationArrayList)
                        response.data.locations = currentLocationList as List<Location>
                    }
                }
                _locations.value = response
            }
        }
    }

    fun getSingleLocation(id: Int) {
        _singleLocation.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getSingleLocation(id)
            var ids: String = ""
            if (response.status == Status.SUCCESS) {
                _singleLocation.value = Resource.success(response.data?.id)
                response.data?.residents?.map {
                    ids = ids + it.substringAfter(Constants.SUB_CHARACTER) + ","
                }
            }
            getCharacters(ids)
        }
    }

    private fun getCharacters(ids: String) {
        _characters.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getMultipleCharacters(ids)
            _characters.value = response
        }
    }
}