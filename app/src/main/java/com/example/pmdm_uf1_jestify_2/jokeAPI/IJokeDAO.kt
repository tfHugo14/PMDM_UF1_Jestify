package com.example.pmdm_uf1_jestify_2.jokeAPI

interface IJokeDAO {
    fun getJoke(urlString: String): Joke?
    fun getJokeByLang(category: String?,lang: String?): Joke?
    fun getJokeByCategory(category: String?): Joke?
}