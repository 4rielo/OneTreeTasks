package org.ascarafia.onetreetasks

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform