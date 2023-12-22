package com.dicoding.capstoneproject.homepage

import com.dicoding.capstoneproject.homepage.ApiClient.apiService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://first-deploy-6xu7fd3j2q-et.a.run.app/"

    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }

    fun getNews(): Call<ResponseNews> {
        return apiService.getNews()
    }
}
