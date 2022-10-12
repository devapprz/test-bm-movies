package com.yusuf.bankmandiri.movies.utils.extensions

import android.content.Context
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.isSuccessful
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import com.google.gson.reflect.TypeToken
import com.yusuf.bankmandiri.movies.BuildConfig
import com.yusuf.bankmandiri.movies.injection.SingletonInjector
import com.yusuf.bankmandiri.movies.utils.ResponseWrapper
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.flow
import org.json.JSONObject

suspend inline fun <reified T> Request.awaitResult(context: Context) = flow {
    val injector = EntryPointAccessors.fromApplication(context, SingletonInjector::class.java)
    val gson = injector.gson
    parameters = listOf("api_key" to BuildConfig.API_KEY)
    val (_, res, data) = awaitStringResponseResult()
    require(res.isSuccessful) { res.responseMessage }
    val fuelError = data.component2()
    require(fuelError == null) { fuelError?.localizedMessage ?: res.responseMessage }
    val payloadJson = JSONObject(data.get())
    val statusMessage = payloadJson.optString("status_message", "")
    require(statusMessage.isEmpty()) { statusMessage }
    val typeToken = object : TypeToken<T>() {}.type
    emit(ResponseWrapper(res.statusCode, "", gson.fromJson<T>(data.get(), typeToken)))
}