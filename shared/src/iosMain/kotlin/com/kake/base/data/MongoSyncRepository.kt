package com.kake.base.data

import com.kake.base.models.Category
import com.kake.base.models.Post
import com.kake.base.util.Constants
import com.kake.base.util.RequestState
import com.kake.base.util.toCommonFlow
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

actual class MongoSyncRepositoryImpl : MongoSyncRepository {
     private val app = App.create(Constants.APP_ID)
     private val user = app.currentUser
     private lateinit var realm: Realm

     init {
         configureTheRealm()
     }

     override fun configureTheRealm() {
         if (user != null) {
             val config = SyncConfiguration.Builder(user, setOf(Post::class))
                 .initialSubscriptions {
                     add(
                         query = it.query(Post::class),
                         name = "Blog Posts"
                     )
                 }
                 .log(LogLevel.ALL)
                 .build()
             realm = Realm.open(config)
         }
     }

     override fun readAllPosts(): Flow<List<Post>> {
         return if (user != null) {
             try {
                 realm.query(Post::class)
                     .asFlow()
                     .map { result ->
                         result.list
                     }
             } catch (e: Exception) {
                 flow { emit(emptyList()) }
             }
         } else {
             flow { emit(emptyList()) }
         }
     }

     override fun searchPostsByTitle(query: String): Flow<List<Post>> {
         return if (user != null) {
             try {
                 realm.query<Post>(query = "title CONTAINS[c] $0", query)
                     .asFlow()
                     .map { result ->
                         result.list
                     }
             } catch (e: Exception) {
                 flow { emit(emptyList()) }
             }
         } else {
             flow { emit(emptyList()) }
         }
     }

     override fun searchPostsByCategory(category: Category): Flow<List<Post>> {
         return if (user != null) {
             try {
                 realm.query<Post>(query = "category == $0", category.name)
                     .asFlow()
                     .map { result ->
                         result.list
                     }
             } catch (e: Exception) {
                 flow { emit(emptyList()) }
             }
         } else {
             flow { emit(emptyList()) }
         }
     }
}