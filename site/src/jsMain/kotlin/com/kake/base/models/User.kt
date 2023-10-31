package com.kake.base.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
actual data class User(
    @SerialName(value = "_id")
    actual val id: String = "",
    actual val username: String = "",
    actual val password: String = "",
)

actual data class UserWithoutPassword(
    @SerialName(value = "_id")
    actual val id: String = "",
    actual val username: String = "",
)