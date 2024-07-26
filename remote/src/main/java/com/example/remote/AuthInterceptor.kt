package com.example.remote

import android.util.Log
import com.example.repository.dataSources.local.LocalDataStoreDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val localPrefsDataStore: LocalDataStoreDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            localPrefsDataStore.getApiKey().first()
        }
        val request = chain.request()
        val newRequest = request.newBuilder()
            .header("token", token)
            .build()
        return chain.proceed(newRequest)
    }
}