package org.ascarafia.onetreetasks.application.location

expect class LocationProviderFactory {
    fun create(): LocationProvider
}