package com.yusuf.bankmandiri.movies.feature.genres.model

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GenreModel
@Inject
constructor(
    private val genreRemote: GenreRemote
) {

    suspend fun findAll() = genreRemote.findAll()

}