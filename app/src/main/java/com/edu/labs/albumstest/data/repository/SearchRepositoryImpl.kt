package com.edu.labs.albumstest.data.repository

import com.edu.labs.albumstest.common.Constants.INTERNET_ERROR
import com.edu.labs.albumstest.common.Constants.NO_DATA_FOUND
import com.edu.labs.albumstest.common.Constants.QUERY_EMPTY
import com.edu.labs.albumstest.data.remote.api.SearchAPI
import com.edu.labs.albumstest.data.remote.converter.SearchAPIConverter
import com.edu.labs.albumstest.data.util.NetworkManager
import com.edu.labs.albumstest.domain.interactor.Resource
import com.edu.labs.albumstest.domain.model.Album
import com.edu.labs.albumstest.domain.repository.SearchRepository
import com.edu.labs.albumstest.domain.util.ParametersUtil.generateSearchParameters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(
    private val searchAPI: SearchAPI,
    private val networkManager: NetworkManager
) : SearchRepository {

    /**
     * @see SearchRepository.searchAlbumsByArtist
     */
    override suspend fun searchAlbumsByArtist(
        query: String,
        explicitEnabled: Boolean
    ): Flow<Resource<MutableList<Album>>> = flow {
        when {
            !networkManager.isInternetActive() -> emit(Resource.Error(Throwable(INTERNET_ERROR)))
            query.isEmpty() -> emit(Resource.Error(Throwable(QUERY_EMPTY)))
            else -> {
                emit(Resource.Loading)
                val resource = try {
                    val options = generateSearchParameters(
                        query = query,
                        mediaType = "music",
                        entity = "album",
                        attribute = "artistTerm",
                        explicitEnabled = explicitEnabled
                    )
                    searchAPI.search(options)?.results?.let { albumsResponse ->
                        val albumsList = SearchAPIConverter.convert(albumsResponse)
                        if (albumsList.isNotEmpty()) {
                            Resource.Success(albumsList)
                        } else Resource.Error(Throwable(NO_DATA_FOUND))
                    } ?: run { Resource.Error(Throwable(NO_DATA_FOUND)) }
                } catch (t: Throwable) {
                    t.printStackTrace()
                    Resource.Error(t)
                }
                emit(resource)
            }
        }
    }

}