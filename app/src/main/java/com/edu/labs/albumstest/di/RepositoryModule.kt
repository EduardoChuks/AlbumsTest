package com.edu.labs.albumstest.di

import com.edu.labs.albumstest.data.repository.SearchRepositoryImpl
import com.edu.labs.albumstest.domain.repository.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<SearchRepository> { SearchRepositoryImpl(get(), get()) }

}