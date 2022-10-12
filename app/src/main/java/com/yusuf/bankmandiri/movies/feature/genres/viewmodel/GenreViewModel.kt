package com.yusuf.bankmandiri.movies.feature.genres.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf.bankmandiri.movies.feature.genres.GenreState
import com.yusuf.bankmandiri.movies.feature.genres.model.GenreModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel
@Inject
constructor(
    private val genreModel: GenreModel
) : ViewModel() {

    private val _state = MutableStateFlow(GenreState())

    val state = _state.asStateFlow()

    fun findAll() {
        viewModelScope.launch(Dispatchers.IO) {
            genreModel.findAll()
                .onStart {
                    _state.update { GenreState(loading = true) }
                }
                .catch { error ->
                    _state.update { GenreState(error = error.localizedMessage) }
                }
                .collectLatest { result ->
                    _state.update { GenreState(genres = result.data?.genres.orEmpty()) }
                }
        }
    }

}