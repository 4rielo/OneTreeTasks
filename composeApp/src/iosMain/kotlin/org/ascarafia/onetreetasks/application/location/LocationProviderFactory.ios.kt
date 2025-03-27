package org.ascarafia.onetreetasks.application.location

actual class LocationProviderFactory {
    actual fun create(): LocationProvider {
        return LocationProviderImpl()
    }
}