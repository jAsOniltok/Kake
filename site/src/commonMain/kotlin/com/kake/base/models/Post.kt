package com.kake.base.models

expect class Post {
    val id: String
    val author: String
    val date: Double
    val title: String
    val subtitle: String
    val thumbnail: String
    val content: String
    val category: Category
    val popular: Boolean
    val main: Boolean
    val sponsored: Boolean
}
