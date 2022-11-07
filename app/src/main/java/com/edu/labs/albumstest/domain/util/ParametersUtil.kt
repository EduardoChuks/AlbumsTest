package com.edu.labs.albumstest.domain.util

object ParametersUtil {

    private const val DEFAULT_LIMIT = 50
    private const val DEFAULT_EXPLICIT = true
    private const val DEFAULT_COUNTRY = "US"

    /**
     * Function to generate search parameters according requirements
     * @see <a href="https://developer.apple.com/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI/Searching.html#//apple_ref/doc/uid/TP40017632-CH5-SW1">Documentation</a>
     */
    fun generateSearchParameters(
        query: String,
        limit: Int = DEFAULT_LIMIT,
        explicitEnabled: Boolean = DEFAULT_EXPLICIT,
        country: String = DEFAULT_COUNTRY,
        mediaType: String?,
        entity: String?,
        attribute: String?
    ): HashMap<String, String> {
        val options = HashMap<String, String>()
        options["term"] = query
        options["limit"] = limit.toString()
        options["explicit"] = if (explicitEnabled) "Yes" else "No"
        options["country"] = country
        mediaType?.let { options["media"] = it }
        entity?.let { options["entity"] = it }
        attribute?.let { options["attribute"] = it }
        return options
    }

}