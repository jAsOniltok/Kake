package com.kake.base.pages

import androidx.compose.runtime.Composable
import com.kake.base.Testing
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.dom.Text


@Page
@Composable
fun HomePage() {
    Column(modifier = Modifier.fillMaxSize().backgroundColor(Color.blue)) {
        Text(Testing().me)
    }
}
