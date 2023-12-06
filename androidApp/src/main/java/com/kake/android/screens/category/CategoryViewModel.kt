package com.kake.android.screens.category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kake.base.data.MongoSyncRepositoryImpl
import com.kake.base.models.Category
import com.kake.base.util.RequestState
import com.kake.base.models.Post
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CategoryViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _categoryPosts: MutableState<RequestState<List<Post>>> =
        mutableStateOf(RequestState.Idle)
    val categoryPosts: State<RequestState<List<Post>>> = _categoryPosts
    private val mongoSyncRepository = MongoSyncRepositoryImpl()

    init {
        _categoryPosts.value = RequestState.Loading
        val selectedCategory = savedStateHandle.get<String>("category")
        if (selectedCategory != null) {
            viewModelScope.launch {
                mongoSyncRepository.searchPostsByCategory(
                    category = Category.valueOf(selectedCategory)
                ).collectLatest { _categoryPosts.value = it }
            }
        }
    }
}