package com.edu.labs.albumstest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.labs.albumstest.domain.interactor.Resource
import com.edu.labs.albumstest.domain.repository.SearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _artistNameQuery = MutableStateFlow("")
    val artistNameQuery: Flow<String> = _artistNameQuery

    private val submitEvent = MutableSharedFlow<Unit>()

    private val resource = submitEvent
        .map { _artistNameQuery.value }
        .flatMapLatest { searchRepository.searchAlbumsByArtist(it, true) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, Resource.Loading)

    val isLoading = resource.map { it.isLoading }
    val error = resource.map { it.error?.localizedMessage }
    val data = resource.map { it.valueOrNull.orEmpty() }

    init {
        search()
    }

    fun updateArtistQuery(artistName: String) {
        _artistNameQuery.value = artistName
    }

    fun search() {
        viewModelScope.launch {
            submitEvent.emit(Unit)
        }
    }

}