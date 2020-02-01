package com.ict.assetmanagement.common.network

/** This class is use to check network connectivity.  */
interface NetworkConnectivityProviderContract {

    /**
     * Check if internet is working or not.
     *
     * @return True if internet is working else return false.
     */
    fun isInternetConnected(): Boolean
}
