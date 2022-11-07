package com.edu.labs.albumstest.domain.repository

import com.edu.labs.albumstest.domain.interactor.Resource
import com.edu.labs.albumstest.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    /**
     * Function to retrieve a list of albums by Artist/Band/Singer name
     * @param query: name or part of the name of some artist
     * @param explicitEnabled: flag to include explicit content in the list retrieved
     */
    suspend fun searchAlbumsByArtist(
        query: String,
        explicitEnabled: Boolean
    ): Flow<Resource<MutableList<Album>>>

}