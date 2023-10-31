package com.kake.base

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp


@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    SilkApp {
        content()
    }
}