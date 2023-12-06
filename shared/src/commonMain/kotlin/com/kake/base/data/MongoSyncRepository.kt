package com.kake.base.data

import com.kake.base.models.Category
import com.kake.base.models.Post
import com.kake.base.util.RequestState
import kotlinx.coroutines.flow.Flow

interface MongoSyncRepository {
    fun configureTheRealm()
    fun readAllPosts(): Flow<RequestState<List<Post>>>
    fun searchPostsByTitle(query: String): Flow<RequestState<List<Post>>>
    fun searchPostsByCategory(category: Category): Flow<RequestState<List<Post>>>
}