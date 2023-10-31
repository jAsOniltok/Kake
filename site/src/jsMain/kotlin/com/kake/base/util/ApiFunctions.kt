package com.kake.base.util

import com.kake.base.models.User
import com.kake.base.models.UserWithoutPassword
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

suspend fun checkUserExistence(user: User): UserWithoutPassword? {
    return try {
        val result = window.api.tryPost(
            apiPath = "usercheck",
            body = Json.encodeToString(user).encodeToByteArray()
        )
        Json.decodeFromString<UserWithoutPassword>(result.toString())
    } catch (e: Exception) {
        println(e.message)
        null
    }
}