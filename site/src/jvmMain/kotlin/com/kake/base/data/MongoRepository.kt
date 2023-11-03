package com.kake.base.data

import com.kake.base.models.User

interface MongoRepository {
    suspend fun checkUserExistence(user:User): User?
    suspend fun checkUserId(id:String): Boolean
}