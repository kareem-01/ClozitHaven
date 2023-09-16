package com.example.remote.utils

enum class HttpCode(val code: Int) {
    BADEMAIL(400),
    BADAUTH(401),
    DUBLICATED_EMAIL(409),
}