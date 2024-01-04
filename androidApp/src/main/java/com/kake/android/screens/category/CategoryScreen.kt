package com.kake.android.screens.category

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.kake.android.components.PostCardsView
import com.kake.base.models.Category
import com.kake.base.util.RequestState
import com.kake.base.models.Post

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    posts: List<Post>,
    category: Category,
    onBackPress: () -> Unit,
    onPostClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = category.name) },
                navigationIcon = {
                    IconButton(onClick = { onBackPress() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Arrow Icon"
                        )
                    }
                }
            )
        }
    ) {
        PostCardsView(
            posts = posts,
            topMargin = it.calculateTopPadding(),
            onPostClick = onPostClick
        )
    }
}