package com.yusuf.bankmandiri.movies.utils

import androidx.annotation.Keep

@Keep
data class ResponseWrapper<T>(
    val status: Int,
    val message: String?,
    val data: T?
)