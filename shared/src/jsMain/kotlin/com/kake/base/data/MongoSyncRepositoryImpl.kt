package com.kake.base.data

import com.kake.base.models.Category
import com.kake.base.models.Post
import com.kake.base.util.RequestState
import kotlinx.coroutines.flow.Flow

actual class MongoSyncRepositoryImpl actual constructor() : MongoSyncRepository {
    override fun configureTheRealm() {
        TODO("Not yet implemented")
    }

    override fun readAllPosts(): Flow<RequestState<List<Post>>> {
        TODO("Not yet implemented")
    }

    override fun searchPostsByTitle(query: String): Flow<RequestState<List<Post>>> {
        TODO("Not yet implemented")
    }

    override fun searchPostsByCategory(category: Category): Flow<RequestState<List<Post>>> {
        TODO("Not yet implemented")
    }
}