package com.edu.labs.albumstest.domain.interactor

sealed class Resource<out T> {

    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val t: Throwable) : Resource<Nothing>()

    val isLoading get() = this is Loading
    val error get() = (this as? Error)?.t
    val valueOrNull get() = (this as? Success)?.data

}