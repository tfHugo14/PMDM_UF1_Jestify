package com.example.pmdm_uf1_jestify_2.jokeApi

enum class Flags(val flag: String) {
    NSFW("nsfw"),
    RELIGIOUS("religious"),
    POLITICAL("political"),
    RACIST("racist"),
    SEXIST("sexist"),
    EXPLICIT("explicit");

    fun getFlag(): String {
        return flag
    }
}