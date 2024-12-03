package com.example.pmdm_uf1_jestify_2.jokeApi

interface IJokeDAO {
    fun getJoke(): Joke?
    fun getJokeByLang(lang: String?): Joke?
    fun getJokeByCategory(category: String?): Joke?
}