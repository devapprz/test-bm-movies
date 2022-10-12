package com.yusuf.bankmandiri.movies.feature.genres.model.data

import com.google.gson.annotations.Expose
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Genre(
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null
)