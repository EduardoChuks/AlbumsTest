package com.edu.labs.albumstest.data.remote.model

/* Example
{
    "resultCount": 57,
    "results": [..AlbumResponse..]
}
*/
data class SearchResponse(
    var resultCount: Int? = null,
    var results: List<AlbumResponse>? = null
)

/* Example
{
    "wrapperType": "collection",
    "collectionType": "Album",
    "artistId": 1352449404,
    "collectionId": 185731554,
    "amgArtistId": 195154,
    "artistName": "JAY-Z & LINKIN PARK",
    "collectionName": "Collision Course (Bonus Video Version) - EP",
    "collectionCensoredName": "Collision Course (Bonus Video Version) - EP",
    "artistViewUrl": "https://music.apple.com/us/artist/jay-z/1352449404?uo=4",
    "collectionViewUrl": "https://music.apple.com/us/album/collision-course-bonus-video-version-ep/185731554?uo=4",
    "artworkUrl60": "https://is5-ssl.mzstatic.com/image/thumb/Music71/v4/17/9f/fa/179ffa90-74cd-0afa-2c6e-5c166b7cd1c3/dj.vnurtdjw.jpg/60x60bb.jpg",
    "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music71/v4/17/9f/fa/179ffa90-74cd-0afa-2c6e-5c166b7cd1c3/dj.vnurtdjw.jpg/100x100bb.jpg",
    "collectionPrice": 14.99,
    "collectionExplicitness": "explicit",
    "contentAdvisoryRating": "Explicit",
    "trackCount": 7,
    "copyright": "℗ 2004 Warner Records Inc. and Roc-A-Fella Records, LLC.",
    "country": "USA",
    "currency": "USD",
    "releaseDate": "2004-11-30T08:00:00Z",
    "primaryGenreName": "Hard Rock"
}
*/
data class AlbumResponse(
    var artistName: String? = null,
    var collectionName: String? = null,
    var collectionCensoredName: String? = null,
    var artworkUrl60: String? = null,
    var artworkUrl100: String? = null,
    var copyright: String? = null,
    var releaseDate: String? = null,
    var primaryGenreName: String? = null,
    var currency: String? = null,
    var collectionPrice: Double? = null
)