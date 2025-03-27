package org.ascarafia.onetreetasks.application.helpers

import platform.Foundation.NSUUID

actual class UUIDGenerator actual constructor() {
    actual fun generateUUID(): String {
        return NSUUID().UUIDString()
    }
}