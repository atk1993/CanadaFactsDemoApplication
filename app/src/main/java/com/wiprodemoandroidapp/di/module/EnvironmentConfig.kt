package com.am.env

import android.content.Context
import android.util.Log
import com.wiprodemoandroidapp.di.module.ConfigKeys.DEV_ENV_CONFIG
import com.wiprodemoandroidapp.di.module.ConfigKeys.KEY_BASE_PATH
import com.wiprodemoandroidapp.di.module.ConfigKeys.KEY_CANADA_FACTS_END_POINT
import com.wiprodemoandroidapp.di.module.ConfigKeys.KEY_GATEWAY_URL
import com.wiprodemoandroidapp.di.module.EnvironmentAttributes
import com.wiprodemoandroidapp.di.module.EnvironmentType
import java.io.IOException
import java.util.*

/**
 * Implementation of [EnvironmentConfigContract].
 */
class EnvironmentConfig(private val context: Context) : EnvironmentConfigContract {

    override fun getEnvironmentAttributes(): EnvironmentAttributes = readEnvironmentProperties().run {
        EnvironmentAttributes(gateWayUrl = getProperty(KEY_GATEWAY_URL),
                basePath = getProperty(KEY_BASE_PATH),
                canadaFactsEndpoint = getProperty(KEY_CANADA_FACTS_END_POINT))
    }

    /**
     * This method read property file and gives properties for selected environmentAttributes.
     *
     * @return instance of properties with contains configurations.
     */
    private fun readEnvironmentProperties(): Properties {
        val properties = Properties()
        try {
            properties.load(context.assets.open(when (getSelectedEnvironment()) {
                EnvironmentType.D -> DEV_ENV_CONFIG
                else -> ""
            }))
        } catch (e: IOException) {
            Log.e(this.javaClass.simpleName, e.toString())
        }
        return properties
    }


    override fun getSelectedEnvironment(): EnvironmentType = EnvironmentType.D

}
