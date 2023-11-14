package com.kake.base.models

import kotlinx.serialization.Serializable
import org.bson.codecs.ObjectIdGenerator

@Serializable
actual data class Post(
    actual val _id: String = ObjectIdGenerator().generate().toString(),
    actual val author: String,
    actual val date: Double,
    actual val title: String,
    actual val subtitle: String,
    actual val thumbnail: String,
    actual val content: String,
    actual val category: Category,
    actual val popular: Boolean = false,
    actual val main: Boolean = false,
    actual val sponsored: Boolean = false,
)

@Serializable
actual data class PostWithoutDetails (
    actual val _id: String = ObjectIdGenerator().generate().toString(),
    actual val author: String,
    actual val date: Double,
    actual val title: String,
    actual val subtitle: String,
    actual val thumbnail: String,
    actual val category: Category,
    actual val popular: Boolean = false,
    actual val main: Boolean = false,
    actual val sponsored: Boolean = false,
)