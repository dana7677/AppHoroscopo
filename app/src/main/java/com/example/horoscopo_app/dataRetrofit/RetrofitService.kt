package com.example.horoscopo_app.dataRetrofit

import com.example.horoscopo_app.dataRetrofit.model.RemoteResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//import retrofit.http.GET
interface RetrofitService {

    // /get-horoscope/daily
    @GET("get-horoscope/daily")
    suspend fun getDailyZodiac(
        @Query("sign") apiKey:String,
        @Query("day") day:String="TODAY"
    ):RemoteResult


}
object RetrofitServiceFactory
{
    fun makeRetrofitService():RetrofitService
    {
        return Retrofit.Builder()
            .baseUrl("https://horoscope-app-api.vercel.app/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}