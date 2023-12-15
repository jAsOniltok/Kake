package com.kake.base.data

import com.kake.base.models.Category
import com.kake.base.models.Newsletter
import com.kake.base.models.Post
import com.kake.base.models.PostWithoutDetails
import com.kake.base.models.User

interface MongoRepository {
    suspend fun addPost(post: Post): Boolean
    suspend fun updatePost(post: Post): Boolean
    suspend fun checkUserExistence(user:User): User?
    suspend fun checkUserId(id:String): Boolean
    suspend fun readMyPosts(skip: Int, author: String): List<PostWithoutDetails>
    suspend fun readMainPosts(): List<PostWithoutDetails>
    suspend fun readLatestPosts(skip: Int): List<PostWithoutDetails>
    suspend fun readSponsoredPosts(): List<PostWithoutDetails>
    suspend fun readPopularPosts(skip: Int): List<PostWithoutDetails>
    suspend fun deleteSelectedPosts(ids: List<String>): Boolean
    suspend fun searchPostsByTittle(query: String, skip: Int): List<PostWithoutDetails>
    suspend fun searchPostsByCategory(category: Category, skip: Int): List<PostWithoutDetails>
    suspend fun readSelectedPost(id: String): Post
    suspend fun subscribe(newsletter: Newsletter): String

}