package com.yusuf.bankmandiri.movies.features.movies.viewmodel

import androidx.lifecycle.ViewModel
import com.yusuf.bankmandiri.movies.features.movies.model.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject
constructor(
    private val movieModel: MovieModel
) : ViewModel() {

}