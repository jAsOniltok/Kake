package com.kake.android.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kake.base.data.MongoSyncRepositoryImpl
import com.kake.base.util.Constants.APP_ID
import com.kake.base.util.RequestState
import com.kake.base.models.Post
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private val _allPosts: MutableState<RequestState<List<Post>>> =
        mutableStateOf(RequestState.Idle)
    val allPosts: State<RequestState<List<Post>>> = _allPosts
    private val _searchedPosts: MutableState<RequestState<List<Post>>> =
        mutableStateOf(RequestState.Idle)
    val searchedPosts: State<RequestState<List<Post>>> = _searchedPosts
    private val mongoSyncRepository = MongoSyncRepositoryImpl()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            App.create(APP_ID).login(Credentials.anonymous())
            fetchAllPosts()
        }
    }

    private suspend fun fetchAllPosts() {
        withContext(Dispatchers.Main) {
            _allPosts.value = RequestState.Loading
        }
        mongoSyncRepository.readAllPosts().collectLatest {
            _allPosts.value = it
        }
    }

    fun searchPostsByTitle(query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _searchedPosts.value = RequestState.Loading
            }
            mongoSyncRepository.searchPostsByTitle(query = query).collectLatest {
                _searchedPosts.value = it
            }
        }
    }

    fun resetSearchedPosts() {
        _searchedPosts.value = RequestState.Idle
    }
}