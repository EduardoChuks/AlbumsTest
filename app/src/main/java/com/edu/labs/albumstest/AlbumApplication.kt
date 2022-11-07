package com.edu.labs.albumstest

import android.app.Application
import com.edu.labs.albumstest.di.networkModule
import com.edu.labs.albumstest.di.repositoryModule
import com.edu.labs.albumstest.di.retrofitModule
import com.edu.labs.albumstest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AlbumApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AlbumApplication)
            androidLogger()
            modules(
                networkModule,
                retrofitModule,
                repositoryModule,
                viewModelModule
            )
        }
    }

}