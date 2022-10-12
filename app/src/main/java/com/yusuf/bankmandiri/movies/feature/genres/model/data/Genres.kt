package com.yusuf.bankmandiri.movies.feature.genres.model.data

import com.google.gson.annotations.Expose
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Genres(
    @SerializedName("genres")
    @Expose
    val genres: List<Genre>? = null
)