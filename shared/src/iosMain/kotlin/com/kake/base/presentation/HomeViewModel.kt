package com.kake.base.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.kake.base.data.MongoSyncRepositoryImpl
import com.kake.base.models.Post
import com.kake.base.util.CommonStateFlow
import com.kake.base.util.Constants
import com.kake.base.util.RequestState
import com.kake.base.util.toCommonStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private val _state: MutableStateFlow<List<Post>> = MutableStateFlow(emptyList())
    val state = _state.toCommonStateFlow()
    private val _allPosts: MutableState<List<Post>> =
        mutableStateOf(emptyList())
    val allPosts: State<List<Post>> = _allPosts
    private val _searchedPosts: MutableState<List<Post>> =
        mutableStateOf(emptyList())
    val searchedPosts: State<List<Post>> = _searchedPosts
    private val mongoSyncRepository = MongoSyncRepositoryImpl()

    init {
        viewModelScope.launch {
            App.create(Constants.APP_ID).login(Credentials.anonymous())
            fetchAllPosts()
        }
    }

    suspend fun fetchAllPosts() {
        withContext(Dispatchers.Main) {
//            _allPosts.value = RequestState.Loading
        }
        mongoSyncRepository.readAllPosts().collectLatest {
            _allPosts.value = it
            println("fetchAllPostssss ${_allPosts.value.size}")
        }
    }

    fun searchPostsByTitle(query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
//                _searchedPosts.value = RequestState.Loading
            }
            mongoSyncRepository.searchPostsByTitle(query = query).collectLatest {
                _searchedPosts.value = it
            }
        }
    }

    fun resetSearchedPosts() {
//        _searchedPosts.value = RequestState.Idle
    }
}
