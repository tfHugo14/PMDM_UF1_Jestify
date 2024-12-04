package com.example.pmdm_uf1_jestify_2.jokeAPI

interface IJokeDAO {
    suspend fun getJoke(urlString: String): Joke?
    suspend fun getJokeByLang(category: String?, lang: String?): Joke?
    suspend fun getJokeByCategory(category: String?): Joke?
}