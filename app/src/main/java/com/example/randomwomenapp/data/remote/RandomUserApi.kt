package com.example.randomwomenapp.data.remote.dto

import retrofit2.http.GET
import retrofit2.http.Query

data class RandomUserResponse(
    val results: List<FemaleDto>,
    val info: Info
)

data class Info(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)

interface RandomUserApi {
    @GET("api/")
    suspend fun getRandomUsers(
        @Query("gender") gender: String,
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String
    ): RandomUserResponse
}