package com.edu.labs.albumstest.data.remote.api

import com.edu.labs.albumstest.data.remote.api.EndPoints.SEARCH
import com.edu.labs.albumstest.data.remote.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchAPI {

    @GET(SEARCH)
    suspend fun search(@QueryMap queryOptions: Map<String, String>): SearchResponse?

}