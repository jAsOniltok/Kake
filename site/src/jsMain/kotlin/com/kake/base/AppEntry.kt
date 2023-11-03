package com.kake.base

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext

@InitSilk
fun updateTheme(ctx: InitSilkContext) {
    // Configure silk here
}

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    SilkApp {
        content()
    }
}