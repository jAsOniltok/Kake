package com.kake.base.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.ObjectIdGenerator


@Serializable
actual data class User(
    @SerialName(value = "_id")
    actual val _id: String = ObjectIdGenerator().generate().toString(),
    actual val username: String = "",
    actual val password: String = "",
)

@Serializable
actual data class UserWithoutPassword(
    @SerialName(value = "_id")
    actual val _id: String = ObjectIdGenerator().generate().toString(),
    actual val username: String = "",
)