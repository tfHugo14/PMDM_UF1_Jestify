package com.example.pmdm_uf1_jestify_2.jokeAPI

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URI


class JokeDAO : IJokeDAO {
    private val gson: Gson = GsonBuilder()
                                .registerTypeAdapter(object : TypeToken<Joke>() {}.type, JokeAdapter())
                                .create()

    override fun getJokeByLang(category: String?,lang: String?): Joke? {
        return getJoke("https://v2.jokeapi.dev/joke/$category?lang=$lang)")
    }

    override fun getJokeByCategory(category: String?): Joke? {
        return getJoke("https://v2.jokeapi.dev/joke/$category)")
    }

    override fun getJoke(urlString:String): Joke? {
        var joke: Joke = Joke()
        try {
            val url = URI(urlString).toURL()
            val inputStream = url.openConnection().getInputStream()
            val reader = BufferedReader(InputStreamReader(inputStream))
            joke = gson.fromJson(reader, Joke::class.java)
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return joke
    }
}