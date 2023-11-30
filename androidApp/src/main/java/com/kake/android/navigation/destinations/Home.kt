package com.kake.android.navigation.destinations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kake.base.models.Category
import com.kake.android.navigation.Screen
import com.kake.android.screens.home.HomeScreen
import com.kake.android.screens.home.HomeViewModel

fun NavGraphBuilder.homeRoute(
    onCategorySelect: (Category) -> Unit,
    onPostClick: (String) -> Unit
) {
    composable(route = Screen.Home.route) {
        val viewModel: HomeViewModel = viewModel()
        var query by remember { mutableStateOf("") }
        var searchBarOpened by remember { mutableStateOf(false) }
        var active by remember { mutableStateOf(false) }

        HomeScreen(
            posts = viewModel.allPosts.value,
            searchedPosts = viewModel.searchedPosts.value,
            query = query,
            searchBarOpened = searchBarOpened,
            active = active,
            onActiveChange = { active = it },
            onQueryChange = { query = it },
            onCategorySelect = onCategorySelect,
            onSearchBarChange = { opened ->
                searchBarOpened = opened
                if (!opened) {
                    query = ""
                    active = false
                    viewModel.resetSearchedPosts()
                }
            },
            onSearch = viewModel::searchPostsByTitle,
            onPostClick = onPostClick
        )
    }
}