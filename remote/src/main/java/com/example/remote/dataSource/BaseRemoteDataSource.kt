package com.example.remote.dataSource

import android.util.Log
import com.example.entity.ErrorResponse
import com.example.entity.utils.BadEmailException
import com.example.entity.utils.NoInternetException
import com.example.entity.utils.NullDataException
import com.example.entity.utils.ServerException
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.net.UnknownHostException

abstract class BaseRemoteDataSource {
    protected suspend fun <T> wrapApiCall(request: suspend () -> Response<T>): T {
        return try {
            val response = request()
            if (response.isSuccessful)
                response.body() ?: throw NullDataException("NUll data ")
            else {
                Log.i("FAIL", response.errorBody().toString())
                Log.i("FAIL", response.message().toString())
                Log.i("FAIL", response.code().toString())
                when (response.code()) {

                    400 -> throw BadEmailException(response.message())
                    else -> throw ServerException("no Internet")
                }
            }
        } catch (e: Exception) {
            throw ServerException(e.message)

        }
    }
}


class ErrorConverter<T : Any>(
    private val retrofit: Retrofit,
    private val errorBodyType: Type
) : Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T? {
        val errorConverter = retrofit.responseBodyConverter<ErrorResponse>(
            errorBodyType,
            arrayOfNulls<Annotation>(0)
        )
        val errorResponse = errorConverter.convert(value)
        value.close()
        return errorResponse as T
    }
}
