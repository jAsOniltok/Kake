package com.kake.base.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.kake.base.data.MongoSyncRepositoryImpl
import com.kake.base.models.Post
import com.kake.base.util.initMongoApp
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : KMMViewModel() {

    private val _allPosts = MutableStateFlow<List<Post>>(viewModelScope, emptyList())
    @NativeCoroutinesState
    val allPosts = _allPosts.asStateFlow()

    private val _searchedPosts = MutableStateFlow<List<Post>>(viewModelScope, emptyList())
    @NativeCoroutinesState
    val searchedPosts = _searchedPosts.asStateFlow()
    private val mongoSyncRepository = MongoSyncRepositoryImpl()

    init {
        viewModelScope.coroutineScope.launch {
            initMongoApp()
            fetchAllPosts()
        }
    }

    fun fetchAllPosts() {
        viewModelScope.coroutineScope.launch(Dispatchers.Main) {
            try {
                println("fetchAllPosts() try")
                mongoSyncRepository.readAllPosts().collect { // collectLatest 대신 collect 사용
                    _allPosts.value = it
                }
            } catch (e: Exception) {
                println("fetchAllPosts() error: $e")
            }
        }
    }


    fun searchPostsByTitle(query: String) {
        viewModelScope.coroutineScope.launch {
            mongoSyncRepository.searchPostsByTitle(query = query).collectLatest {
                _searchedPosts.value = it
            }
        }
    }

    fun resetSearchedPosts() {
        _searchedPosts.value = emptyList()
    }
}
