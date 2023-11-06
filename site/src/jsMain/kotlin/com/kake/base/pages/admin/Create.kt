package com.kake.base.pages.admin

import androidx.compose.runtime.Composable
import com.kake.base.components.SidePanel
import com.kake.base.util.Constants
import com.kake.base.util.isUserLoggedIn
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.px

@Page
@Composable
fun CreatePage(){
    isUserLoggedIn {
        CreateScreen()
    }
}

@Composable
fun CreateScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize().maxWidth(Constants.PAGE_WIDTH.px)) {
            SidePanel {

            }
        }
    }
}