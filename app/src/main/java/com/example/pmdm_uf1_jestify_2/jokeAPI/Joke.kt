package com.example.pmdm_uf1_jestify_2.jokeAPI

data class Joke(
    var error: Boolean? = false,
    var category: String? = null,
    var type: String? = null,
    var joke: String? = null,
    var setup: String? = null,
    var delivery: String? = null,
    var flags: Map<Flags, Boolean>? = null,
    var id: Int? = 0,
    var safe: Boolean? = false,
    var lang: String? = null
)