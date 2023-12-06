package com.kake.base.util


fun String.cleanupImageString(): String {
    return this.replace("data:image/png;base64,", "")
        .replace("data:image/jpeg;base64,", "")
}

