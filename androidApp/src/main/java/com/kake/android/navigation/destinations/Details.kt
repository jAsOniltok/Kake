package com.kake.android.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kake.android.navigation.Screen
import com.kake.android.screens.details.DetailsScreen
import com.kake.android.util.Constants.POST_ID_ARGUMENT
import com.kake.base.Constants.SHOW_SECTIONS_PARAM

fun NavGraphBuilder.detailsRoute(
    onBackPress: () -> Unit
) {
    composable(
        route = Screen.Details.route,
        arguments = listOf(navArgument(name = POST_ID_ARGUMENT) {
            type = NavType.StringType
        })
    ) {
        val postId = it.arguments?.getString(POST_ID_ARGUMENT)
        DetailsScreen(
            url = "http://10.0.2.2:8080/posts/post?${POST_ID_ARGUMENT}=$postId&${SHOW_SECTIONS_PARAM}=false",
            onBackPress = onBackPress
        )
    }
}