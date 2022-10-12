package com.yusuf.bankmandiri.movies.feature.genres.model.data

import com.google.gson.annotations.Expose
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Keep
@Entity(tableName = "genre")
data class Genre(
    @SerializedName("id")
    @Expose
    @PrimaryKey()
    val id: Int = 0,
    @SerializedName("name")
    @Expose
    val name: String? = null
)