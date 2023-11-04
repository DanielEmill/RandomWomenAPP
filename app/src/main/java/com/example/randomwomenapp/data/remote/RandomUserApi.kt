package com.example.randomwomenapp.data.remote.dto

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET("api/")
    suspend fun getRandomUsers(
        @Query("gender") gender: String,
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String
    ): List<FemaleDto>
}

