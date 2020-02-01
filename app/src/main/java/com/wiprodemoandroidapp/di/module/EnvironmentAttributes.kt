package com.wiprodemoandroidapp.di.module

/**
 * Environment Attributes.
 */
data class EnvironmentAttributes(var gateWayUrl: String,
                                 var canadaFactsEndpoint: String,
                                 var basePath: String
) {

    fun getGateWayBaseUrl() = gateWayUrl.plus(basePath)

    fun getCanadaFactsBaseURL() = getGateWayBaseUrl().plus(canadaFactsEndpoint)

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}