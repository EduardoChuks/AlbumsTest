package com.edu.labs.albumstest.data.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.ConnectivityManager.TYPE_MOBILE
import android.net.ConnectivityManager.TYPE_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.os.Build

class NetworkManager(private val context: Context) {

    @Suppress("DEPRECATION")
    fun isInternetActive(): Boolean {
        var result = false
        val connManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val ntwCapabilities = connManager.activeNetwork ?: return false
            val actNw = connManager.getNetworkCapabilities(ntwCapabilities) ?: return false
            result = when {
                actNw.hasTransport(TRANSPORT_WIFI) -> true
                actNw.hasTransport(TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connManager.activeNetworkInfo?.run {
                result = when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return result
    }

}