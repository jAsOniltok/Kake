package com.kake.base.pages.admin

import androidx.compose.runtime.Composable
import com.kake.base.util.isUserLoggedIn
import com.varabyte.kobweb.core.Page

@Page
@Composable
fun HomeScreen(){
    isUserLoggedIn {
        HomePage()
    }
}

@Composable
fun HomePage(){
    println("Admin Home Page")
}