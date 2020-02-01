package com.ict.assetmanagement.common.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 *Implementation of [NetworkConnectivityProviderContract]
 */
class NetworkConnectivityProvider(private val context: Context) : NetworkConnectivityProviderContract {

    override fun isInternetConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                getNetworkCapabilities(this)
            } ?: false
        } else {
            @Suppress("DEPRECATION")
            connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
        }
    }

    /**
     * Get the network capabilities.
     *
     * @param networkCapabilities: Object of [NetworkCapabilities]
     */
    private fun getNetworkCapabilities(networkCapabilities: NetworkCapabilities): Boolean = networkCapabilities.run {
        when {
            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}
