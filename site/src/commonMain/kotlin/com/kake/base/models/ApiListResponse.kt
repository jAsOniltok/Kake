package com.kake.base.models

expect sealed class ApiListResponse {
    object Idle
    class Success
    class Error
}