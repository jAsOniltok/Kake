package com.kake.base

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.App


@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    content()
}