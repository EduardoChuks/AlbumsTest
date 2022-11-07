package com.edu.labs.albumstest.util.extensions

import androidx.appcompat.widget.SearchView

fun SearchView.setQuery(query: String) {
    val current = this.query.toString()
    if (current != query) {
        setQuery(query, false)
    }
}