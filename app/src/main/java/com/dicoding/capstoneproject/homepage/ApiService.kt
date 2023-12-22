package com.dicoding.capstoneproject.homepage

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/news")
    fun getNews(): Call<ResponseNews>
}