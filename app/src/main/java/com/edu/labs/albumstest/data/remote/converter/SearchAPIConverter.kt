package com.edu.labs.albumstest.data.remote.converter

import com.edu.labs.albumstest.data.remote.model.AlbumResponse
import com.edu.labs.albumstest.domain.model.Album
import com.edu.labs.albumstest.domain.util.CurrencyUtil.getCurrencySymbol
import com.edu.labs.albumstest.domain.util.DateUtil
import com.edu.labs.albumstest.domain.util.DateUtil.yyyyMMddTHHmmssZ
import com.edu.labs.albumstest.util.extensions.notNull

object SearchAPIConverter {

    fun convert(
        albumsResponse: List<AlbumResponse>,
        explicitEnabled: Boolean = true
    ): MutableList<Album> {
        val albums = mutableListOf<Album>()
        albumsResponse.forEach { albumResponse ->
            albums.add(
                Album().apply {
                    artistName = albumResponse.artistName.notNull()
                    collectionName =
                        if (explicitEnabled) albumResponse.collectionName.notNull()
                        else albumResponse.collectionCensoredName.notNull()
                    // I've tried the 60x60 resolution artwork but it looks weird
                    artworkURL = albumResponse.artworkUrl100.notNull()
                    copyright = albumResponse.copyright.notNull()
                    genreName = albumResponse.primaryGenreName.notNull()
                    price = "${getCurrencySymbol(albumResponse.currency)} ${albumResponse.collectionPrice ?: 0.0}"
                    releaseDate = DateUtil.parseDate(yyyyMMddTHHmmssZ, albumResponse.releaseDate)
                }
            )
        }
        return albums
    }

}