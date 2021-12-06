package ru.kavunov.mtsproject.recponse

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

import java.util.concurrent.TimeUnit

class App : Application() {

    init {
        instance = this
    }

    val apiService: ApiService by lazy { ApiService.create() }

    companion object {
        //        lateinit var instance: App
        var instance: App = App()
            private set
    }
}
fun Retrofit.Builder.setClient() = apply {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    this.client(okHttpClient)
}
@Suppress("EXPERIMENTAL_API_USAGE")
fun Retrofit.Builder.addJsonConverter() = apply {
    val json = Json { ignoreUnknownKeys = true }
    val contentType = APPLICATION_JSON_TYPE.toMediaType()
    this.addConverterFactory(json.asConverterFactory(contentType))
}