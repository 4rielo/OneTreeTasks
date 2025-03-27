package org.ascarafia.onetreetasks.application.location

import dev.jordond.compass.Location

actual class LocationProviderImpl : LocationProvider {
    override suspend fun getCurrentLocation(): Location? {
        return null
    }
}