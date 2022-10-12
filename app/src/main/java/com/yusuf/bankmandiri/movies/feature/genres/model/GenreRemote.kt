package com.yusuf.bankmandiri.movies.feature.genres.model

import android.content.Context
import com.github.kittinunf.fuel.httpGet
import com.yusuf.bankmandiri.movies.feature.genres.model.data.Genres
import com.yusuf.bankmandiri.movies.utils.extensions.awaitResult
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GenreRemote
@Inject
constructor(
    @ApplicationContext
    private val context: Context
) {

    suspend fun findAll() = "genre/movie/list"
        .httpGet()
        .awaitResult<Genres>(context)

}