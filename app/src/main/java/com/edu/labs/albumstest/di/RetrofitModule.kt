package com.edu.labs.albumstest.di

import com.edu.labs.albumstest.BuildConfig
import com.edu.labs.albumstest.data.remote.api.SearchAPI
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {

    single { createHttpClient() }

    single {
        createRetrofit(
            okHttpClient = get(),
            baseUrl = BuildConfig.BASE_API_URL
        )
    }

    single {
        createWebService<SearchAPI>(retrofit = get())
    }

}

fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(300, TimeUnit.SECONDS)
    client.connectTimeout(300, TimeUnit.SECONDS)
    client.addInterceptor(createLogInterceptor())
    return client.build()
}

fun createLogInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()

    if (BuildConfig.DEBUG) interceptor.setLevel(HttpLoggingInterceptor.Level.BODY) // only for devs
    else interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC) // prod or beta

    return interceptor
}

fun createRetrofit(
    okHttpClient: OkHttpClient,
    baseUrl: String
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(okHttpClient)
        .build()
}

inline fun <reified ApiType> createWebService(retrofit: Retrofit): ApiType {
    return retrofit.create(ApiType::class.java)
}