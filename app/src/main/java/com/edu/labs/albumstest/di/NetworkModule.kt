package com.edu.labs.albumstest.di

import com.edu.labs.albumstest.data.util.NetworkManager
import org.koin.dsl.module

val networkModule = module {

    single { NetworkManager(get()) }

}