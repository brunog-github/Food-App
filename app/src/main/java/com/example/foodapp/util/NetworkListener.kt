package com.example.foodapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkListener: ConnectivityManager.NetworkCallback() {

    private val isNetworkAvailable = MutableStateFlow(false)

    fun checkNetworkAvailability(context: Context): MutableStateFlow<Boolean> {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(this)

        val network = connectivityManager.activeNetwork
        if(connectivityManager.activeNetwork == null) isNetworkAvailable.value = false

        val actNetwork = connectivityManager.getNetworkCapabilities(network)
        if(actNetwork == null) {
            isNetworkAvailable.value = false
        } else {
            when {
                actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    isNetworkAvailable.value = true
                }
                actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    isNetworkAvailable.value = true
                }
                else -> isNetworkAvailable.value = false
            }
        }
        return isNetworkAvailable
    }

    override fun onAvailable(network: Network) {
        isNetworkAvailable.value = true
    }

    override fun onLost(network: Network) {
        isNetworkAvailable.value = false
    }
}
