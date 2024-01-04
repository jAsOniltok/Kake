package com.kake.base.util

import com.kake.base.models.Post
import kotlinx.serialization.Serializable

sealed class RequestState {
    data object Idle : RequestState()
    data object Loading : RequestState()
    data class Success(val data: List<Post>) : RequestState()
    data class Error(val error: Exception) : RequestState()

    fun getPosts(): List<Post> {
        return when (this) {
            is Success -> data
            else -> emptyList()
        }
    }
}