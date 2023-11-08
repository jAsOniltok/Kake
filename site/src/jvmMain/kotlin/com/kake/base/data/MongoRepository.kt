package com.kake.base.data

import com.kake.base.models.Post
import com.kake.base.models.User

interface MongoRepository {
    suspend fun addPost(post:Post): Boolean
    suspend fun updatePost(post: Post): Boolean
    suspend fun checkUserExistence(user:User): User?
    suspend fun checkUserId(id:String): Boolean
}