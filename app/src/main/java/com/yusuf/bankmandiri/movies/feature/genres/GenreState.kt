package com.yusuf.bankmandiri.movies.feature.genres

import androidx.annotation.Keep
import com.yusuf.bankmandiri.movies.feature.genres.model.data.Genre

@Keep
data class GenreState(
    val loading: Boolean = false,
    val error: String? = "",
    val genres: List<Genre>? = null
)