package com.example.pmdm_uf1_jestify_2.jokeAPI

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class JokeAdapter: JsonDeserializer<Joke> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Joke {
        val jsonObject = json?.asJsonObject

        try {
            return Joke(
                error = jsonObject?.get("error")?.asBoolean ?: false,
                category = jsonObject?.get("category")?.asString,
                type = jsonObject?.get("type")?.asString,
                joke = jsonObject?.get("joke")?.asString,
                setUp = jsonObject?.get("setup")?.asString,
                delivery = jsonObject?.get("delivery")?.asString,
                flags = jsonObject?.getAsJsonObject("flags")?.entrySet()?.associate {
                    it.key to it.value.asBoolean
                },
                id = jsonObject?.get("id")?.asInt,
                safe = jsonObject?.get("safe")?.asBoolean,
                lang = jsonObject?.get("lang")?.asString
            )
        } catch (e: Exception) {
            println("Error parsing Joke: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
}