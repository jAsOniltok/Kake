package com.kake.base.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.kake.base.data.MongoSyncRepositoryImpl
import com.kake.base.models.Post
import com.kake.base.util.initMongoApp
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : KMMViewModel() {
    private val _state = MutableStateFlow<List<Post>>(emptyList())
    override fun onCleared() {
        super.onCleared()
    }

    @NativeCoroutinesState
    val state = _state.asStateFlow()

    private val _allPosts: MutableState<List<Post>> =
        mutableStateOf(emptyList())
    val allPosts: State<List<Post>> = _allPosts
    private val _searchedPosts: MutableState<List<Post>> =
        mutableStateOf(emptyList())
    val searchedPosts: State<List<Post>> = _searchedPosts
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
                    _state.value = it
                    println("_state.value: ${_state.value.size}")
                }
            } catch (e: Exception) {
                println("fetchAllPosts() error: $e")
            }
        }
    }


    fun searchPostsByTitle(query: String) {
        viewModelScope.coroutineScope.launch {
            withContext(Dispatchers.Main) {
//                _searchedPosts.value = RequestState.Loading
            }
            mongoSyncRepository.searchPostsByTitle(query = query).collectLatest {
                _searchedPosts.value = it
            }
        }
    }

    fun getStateValue() = state.value

    fun resetSearchedPosts() {
//        _searchedPosts.value = RequestState.Idle
    }
}
