package com.yusuf.bankmandiri.movies.injection

import com.google.gson.Gson
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@EntryPoint
interface SingletonInjector {
    val gson: Gson
}