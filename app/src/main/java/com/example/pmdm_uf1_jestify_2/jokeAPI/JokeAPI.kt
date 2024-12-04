package com.example.pmdm_uf1_jestify_2.jokeAPI

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokeAPI {
    @GET("joke/{category}")
    suspend fun getJokeByCategory(
        @Path("category") category: String,
        @Query("lang") lang: String
    ): Joke
}