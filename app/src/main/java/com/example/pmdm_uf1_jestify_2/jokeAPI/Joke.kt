package com.example.pmdm_uf1_jestify_2.jokeAPI

data class Joke (
    private var error: Boolean? = false,
    private var category: String? = null,
    private var type: String? = null,
    private var joke: String? = null,
    private var setUp: String? = null,
    private var delivery: String? = null,
    private var flags: Map<Flags?, Boolean>? = null,
    private var id: Int? = 0,
    private var safe: Boolean? = false,
    private var lang: String? = null
)