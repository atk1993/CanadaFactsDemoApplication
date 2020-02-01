package com.wiprodemoandroidapp

import android.content.Context

/**
 * Interface to be implemented by the Application class to provider the Accessors.
 */
interface AppContentProvider {
    /**
     * Get the app context
     */
    fun getContext(): Context
}