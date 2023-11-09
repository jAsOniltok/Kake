package com.kake.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ApiResponse {
    @Serializable
    @SerialName("idle")
    data object Idle: ApiResponse()
    @Serializable
    @SerialName("success")
    data class Success(val data: List<Person>): ApiResponse()
    @Serializable
    @SerialName("error")
    data class Error(val errorMessage: String): ApiResponse()
}
