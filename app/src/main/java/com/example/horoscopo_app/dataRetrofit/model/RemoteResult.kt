package com.example.horoscopo_app.dataRetrofit.model

data class RemoteResult(
    val `data`: Data,
    val status: Int,
    val success: Boolean
)