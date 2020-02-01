package com.am.env

import com.wiprodemoandroidapp.di.module.EnvironmentAttributes
import com.wiprodemoandroidapp.di.module.EnvironmentType

/**
 * Contract to setup the build & environment of the application.
 */
interface EnvironmentConfigContract {

    /**
     * Method to get the selected environment Attributes.
     *
     * @return object [EnvironmentAttributes]
     */
    fun getEnvironmentAttributes(): EnvironmentAttributes

    /**
     * Update the environment for the given [EnvironmentType]
     *
     * @return saved the environment flavor.
     */
    fun getSelectedEnvironment(): EnvironmentType
}