package com.edu.labs.albumstest.di

import com.edu.labs.albumstest.ui.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    @OptIn(ExperimentalCoroutinesApi::class)
    viewModel { HomeViewModel(get()) }

}