package com.kake.base.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.id.ObjectIdGenerator


@Serializable
actual data class User(
    @SerialName(value = "_id")
    actual val id: String = ObjectIdGenerator.newObjectId<String>().id.toString(),
    actual val username: String = "",
    actual val password: String = "",
)

actual data class UserWithoutPassword(
    @SerialName(value = "_id")
    actual val id: String = ObjectIdGenerator.newObjectId<String>().id.toString(),
    actual val username: String = "",
)