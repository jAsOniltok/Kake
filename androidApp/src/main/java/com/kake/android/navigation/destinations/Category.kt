package com.kake.android.navigation.destinations

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kake.base.models.Category
import com.kake.android.navigation.Screen
import com.kake.android.screens.category.CategoryScreen
import com.kake.android.screens.category.CategoryViewModel
import com.kake.base.util.Constants.CATEGORY_ARGUMENT

fun NavGraphBuilder.categoryRoute(
    onBackPress: () -> Unit,
    onPostClick: (String) -> Unit
) {
    composable(
        route = Screen.Category.route,
        arguments = listOf(navArgument(name = CATEGORY_ARGUMENT) {
            type = NavType.StringType
        })
    ) {
        val viewModel: CategoryViewModel = viewModel()
        val selectedCategory = it.arguments?.getString(CATEGORY_ARGUMENT) ?: Category.Programming.name
        CategoryScreen(
            posts = viewModel.categoryPosts.value,
            category = Category.valueOf(selectedCategory),
            onBackPress = onBackPress,
            onPostClick = onPostClick
        )
    }
}