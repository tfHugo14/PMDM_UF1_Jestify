package com.example.pmdm_uf1_jestify_2.jokeAPI

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URI
import java.net.URLEncoder

class JokeDAO : IJokeDAO {
    private val gson: Gson = Gson()
    private val client = OkHttpClient()

    override suspend fun getJokeByLang(category: String?, lang: String?): Joke? {
        val url = "https://v2.jokeapi.dev/joke/$category?lang=$lang"
        return getJoke(url)
    }

    override suspend fun getJokeByCategory(category: String?): Joke? {
        val url = "https://v2.jokeapi.dev/joke/$category"
        return getJoke(url)
    }

    override suspend fun getJoke(url: String): Joke? = withContext(Dispatchers.IO) {
        // This method uses a coroutine because main doesn't allow internet access
        try {
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                val jsonResponse = response.body?.string()
                println("Raw JSON Response: $jsonResponse")

                // Parse the response
                return@withContext gson.fromJson(jsonResponse, Joke::class.java)
            } else {
                println("Failed to fetch joke: ${response.code}")
            }
        } catch (e: JsonSyntaxException) {
            println("Json Parsing Error: ${e.message}")
            e.printStackTrace()
        } catch (e: Exception) {
            println("Error fetching joke: ${e.message}")
            e.printStackTrace()
        }
        return@withContext null
    }
}