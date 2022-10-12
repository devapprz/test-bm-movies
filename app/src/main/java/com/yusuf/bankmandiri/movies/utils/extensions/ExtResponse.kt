package com.yusuf.bankmandiri.movies.utils.extensions

import android.content.Context
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.isSuccessful
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import com.google.gson.reflect.TypeToken
import com.yusuf.bankmandiri.movies.BuildConfig
import com.yusuf.bankmandiri.movies.injection.SingletonInjector
import com.yusuf.bankmandiri.movies.utils.base.ResponseWrapper
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.flow
import org.json.JSONObject

suspend inline fun <reified T> Request.awaitResult(context: Context) = flow {
    val injector = EntryPointAccessors.fromApplication(
        context,
        SingletonInjector::class.java
    )
    val gson = injector.gson
    parameters = listOf("api_key" to BuildConfig.API_KEY)
    val (_, res, data) = awaitStringResponseResult()
    require(res.isSuccessful) {
        gson.toJson(
            ResponseWrapper<T>(
                res.statusCode,
                res.responseMessage,
                null
            )
        )
    }
    data.component2()?.apply {
        val message = gson.toJson(
            ResponseWrapper<T>(
                res.statusCode,
                localizedMessage ?: res.responseMessage,
                null
            )
        )
        throw Exception(message)
    }
    val payloadJson = JSONObject(data.get())
    val statusMessage = payloadJson.optString("status_message", "")
    require(statusMessage.isEmpty()) {
        gson.toJson(
            ResponseWrapper<T>(
                payloadJson.optInt("status_code", res.statusCode),
                statusMessage,
                null
            )
        )
    }
    val typeToken = object : TypeToken<T>() {}.type
    val result = ResponseWrapper(res.statusCode, "success", gson.fromJson<T>(data.get(), typeToken))
    emit(result)
}